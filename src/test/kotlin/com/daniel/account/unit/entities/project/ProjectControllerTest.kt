import app.entities.project.Project
import app.entities.project.ProjectController
import app.entities.project.ProjectRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProjectControllerTest {

    @Mock
    lateinit private var projectRepository: ProjectRepository
    lateinit private var controller: ProjectController

    private val userA = "1"
    private val userB = "2"
    private val userC = "3"

    private val projects = listOf(
            Project("a", "1", false, listOf("1", "2"), id = "1"),
            Project("b", "2", true, listOf("2"), id = "2"),
            Project("c", "1", false, listOf("1"), id = "3")
    )

    private val projectsId = listOf(
            "1", "2", "3"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = ProjectController(projectRepository)
    }

    @Test
    fun oneCannotSeePrivateProjectsWhenNotMember() {
        Mockito.`when`(projectRepository.findAll(Mockito.any<List<String>>())).thenReturn(projects)

        assert(controller.projects(projectsId, userA).size == 3)
        assert(controller.projects(projectsId, userB).size == 2)
        assert(controller.projects(projectsId, userC).size == 1)
    }
}