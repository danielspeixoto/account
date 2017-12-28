package config

import app.entities.project.ProjectRepository
import app.entities.user.UserRepository
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
open class Config {

    @Bean
    @Primary
    open fun userRepository(): UserRepository {
        return Mockito.mock(UserRepository::class.java)
    }

    @Bean
    @Primary
    open fun projectRepository(): ProjectRepository {
        return Mockito.mock(ProjectRepository::class.java)
    }
}