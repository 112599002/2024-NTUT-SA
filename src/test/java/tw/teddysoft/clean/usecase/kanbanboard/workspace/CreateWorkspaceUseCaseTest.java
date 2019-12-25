package tw.teddysoft.clean.usecase.kanbanboard.workspace;

import org.junit.Before;
import org.junit.Test;
import tw.teddysoft.clean.adapter.gateway.kanbanboard.InMemoryAggregateRootRepositoryPeer;
import tw.teddysoft.clean.adapter.presenter.kanbanboard.workspace.SingleWorkspacePresenter;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceInput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceOutput;
import tw.teddysoft.clean.usecase.kanbanboard.workspace.create.CreateWorkspaceUseCase;

import static org.junit.Assert.*;

public class CreateWorkspaceUseCaseTest {

    public static final String USER_ID = "USER_007";
    public static final String WORKSPACE_NAME = "Teddy's Workspace";

    @Before
    public void setUp(){
    }

    @Test
    public void add_a_workspace() {

        WorkspaceRepository repository = new WorkspaceRepository(new InMemoryAggregateRootRepositoryPeer());
        CreateWorkspaceOutput output = doCreateWorkspaceUseCase(repository, USER_ID, WORKSPACE_NAME);

        assertNotNull(output.getWorkspaceId());
        assertNotNull(repository.findById(output.getWorkspaceId()));
        assertEquals(USER_ID, repository.findById(output.getWorkspaceId()).getUserId());
        assertEquals(1, repository.findAll().size());
        assertEquals(WORKSPACE_NAME, repository.findAll().get(0).getName());
    }


    public static CreateWorkspaceOutput doCreateWorkspaceUseCase(WorkspaceRepository repository, String userId, String workspaceName){

        UseCase<CreateWorkspaceInput, CreateWorkspaceOutput> createWorkspaceUC = new CreateWorkspaceUseCase(repository);
        CreateWorkspaceInput input = createWorkspaceUC.createInput();
        CreateWorkspaceOutput output = new SingleWorkspacePresenter();
        input.setUserId(userId);
        input.setWorkspaceName(workspaceName);

        createWorkspaceUC.execute(input, output);

        return output;
    }


}