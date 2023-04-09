package com.example.bulletinboardproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
// @Table(name ="") : DB에 생성할 테이블 이름을 지정할 때 사용합니다. (엔티티와 매핑할 테이블을 지정.)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
// (1) Article 클래스: '게시판' 도메인(구조)를 자바 클래스로 표현.
// => id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy
public class Article extends AuditingFields {
    // @Column : 객체 필드를 테이블의 컬럼에 매핑시켜주는 어노테이션입니다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY 전략은 기본 키 생성을 데이터베이스에 위임하는 전략입니다.
    // ex) MySQL의 AUTO_INCREMENT 기능은 데이터베이스가 기본 키를 자동으로 생성해준다.
    // => id가 null일 경우 해당 객체의 Id를 DB의 AUTO_INCREMENT를 가져와 할당한다.
    private Long id;
    // Setter을 각 필드로 제한을 두는 이유 : 이용자가 특정 필드의 접근을 막고자 하기 때문.
    // => 자동으로 번호를 부여하기 때문.

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문
    @Setter private String hashtag; // 해시태그

    @ToString.Exclude
    // @ToString.Exclude : 게시글에서 댓글을 볼 때,
    //                     순환 참조가 될 수 있기 때문에 둘 중 하나의 연결 고리를 끊어줌.
    @OrderBy("id")
    // @OrderBy("id") : 게시글 id 순서대로 배치.
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    // @OneToMany : 양방향 바인딩 -> 1:N 관계 (게시판 : 게시글 안에 댓글)를 표현.
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // 기본 생성자 : 평소에 오픈을 하지 않을 예정이기 때문에 protected로 지정.
    protected Article() {}

    // Setter 데이터만 생성자를 통해서 제공.
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // 메소드를 사용할 때, 가이드를 작성해주는 메소드.
    // => (현재 Article 도메인을 생성하고자 할 때, 밑의 매개변수만 사용할 수 있다는 것을 암시)
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // equals() : 만약 게시판 리스트에 담아서 게시글을 보여주고자 할 때,
    //            중복요소를 제거하거나, 정렬을 해야할 때 사용.
    // But. @EqualsHashCode가 있지만, DB의 Entity를 비교할 때,
    //      게시판 id만 같으면 동일 객체이기 때문에 @EqualsHashCode을 사용할 필요가 없어짐.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}