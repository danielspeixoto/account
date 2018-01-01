package app.entities.project

import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<Project, String>