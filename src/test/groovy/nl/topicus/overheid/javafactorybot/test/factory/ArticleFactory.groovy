package nl.topicus.overheid.javafactorybot.test.factory

import nl.topicus.overheid.javafactorybot.Factory
import nl.topicus.overheid.javafactorybot.definition.Attribute
import nl.topicus.overheid.javafactorybot.definition.Trait
import nl.topicus.overheid.javafactorybot.test.model.Article

import java.util.concurrent.TimeUnit

class ArticleFactory extends Factory<Article> {
    @Override
    Map<String, Attribute> getAttributes() {
        [
                title       : value { faker.lorem().sentence() },
                content     : value { faker.lorem().paragraph() },
                creationDate: value { faker.date().past(20, TimeUnit.DAYS) },
                summary     : value { null },
                author      : hasOne(UserFactory),
                comments    : hasMany(CommentFactory)
        ]
    }

    @Override
    void onAfterBuild(Article article) {
        article?.comments.each { it.article = article }
    }

    @Override
    Map<String, Trait> getTraits() {
        [
                withComments: new Trait() {
                    @Override
                    Map<String, Attribute> getAttributes() {
                        [
                                comments: hasMany(CommentFactory, 3, article: null as Article)
                        ]
                    }
                }
        ]
    }
}