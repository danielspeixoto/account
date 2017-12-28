package app.entities.project

import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectController @Autowired constructor(
        private val repository: ProjectRepository
) {

    fun projects(idList : List<String>, env : DataFetchingEnvironment) : List<Project> {
        return repository.findAll(idList) as List<Project>
    }
}