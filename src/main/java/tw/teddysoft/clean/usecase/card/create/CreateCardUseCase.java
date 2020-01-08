package tw.teddysoft.clean.usecase.card.create;

import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.card.Card;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.usecase.UseCase;
import tw.teddysoft.clean.domain.usecase.repository.Repository;

public class CreateCardUseCase implements UseCase<CreateCardInput, CreateCardOutput> {

    private final Repository<Card> cardRepository;
    private final Repository<Workflow> workflowRepository;
    private final DomainEventBus eventBus;


    public CreateCardUseCase(Repository cardRepository, Repository workflowRepository, DomainEventBus eventBus) {

        this.cardRepository = cardRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }


    @Override
    public void execute(CreateCardInput input, CreateCardOutput output) {
        Card card = new Card(input.getName(), input.getBoardId(), input.getWorkflowId(), input.getLaneId());
        cardRepository.save(card);
        eventBus.postAll(card);

//        Workflow workflow = workflowRepository.findById(input.getWorkflowId());
//        workflow.commitCard(card.getId(), input.getLaneId());
//        workflowRepository.save(workflow);

        output.setId(card.getId());
    }

    @Override
    public CreateCardInput createInput() {
        return new CreateCardInputImpl();
    }

    private static class CreateCardInputImpl implements CreateCardInput {

        private String name;
        private String boardId;
        private String workflowId;
        private String laneId;

        @Override
        public CreateCardInput setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CreateCardInput setBoardId(String boardId) {
            this.boardId = boardId;
            return this;
        }

        @Override
        public CreateCardInput setLaneId(String laneId) {
            this.laneId = laneId;
            return this;
        }

        @Override
        public CreateCardInput setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
            return this;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getBoardId() {
            return boardId;
        }

        @Override
        public String getLaneId() {
            return laneId;
        }

        @Override
        public String getWorkflowId() {
            return workflowId;
        }
    }
}
