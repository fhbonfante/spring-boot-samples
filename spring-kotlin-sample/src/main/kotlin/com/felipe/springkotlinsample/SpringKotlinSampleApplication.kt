package com.felipe.springkotlinsample

import com.samskivert.mustache.Mustache
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class SpringKotlinSampleApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinSampleApplication>(*args)
}

@Bean
fun mustacheCompiler(loader: Mustache.TemplateLoader?) =
		Mustache.compiler().escapeHTML(false).withLoader(loader)

@Bean
fun databaseInitializer(userRepository: UserRepository,
						articleRepository: ArticleRepository) =  {
	val smaldini = User("smaldini", "Stéphane", "Maldini")
	userRepository.save(smaldini)

	articleRepository.save(Article(
			"Reactor Bismuth is out",
			"Lorem ipsum",
			"dolor **sit** amet https://projectreactor.io/",
			smaldini,
			1
	))
	articleRepository.save(Article(
			"Reactor Aluminium has landed",
			"Lorem ipsum",
			"dolor **sit** amet https://projectreactor.io/",
			smaldini,
			2
	))
}