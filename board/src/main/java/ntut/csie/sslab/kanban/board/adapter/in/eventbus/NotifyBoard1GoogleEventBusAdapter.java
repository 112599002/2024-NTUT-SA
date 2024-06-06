package ntut.csie.sslab.kanban.board.adapter.in.eventbus;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.kanban.board.usecase.port.in.notify.NotifyBoard1;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

public class NotifyBoard1GoogleEventBusAdapter {
    private final NotifyBoard1 notifyBoard1;

    public NotifyBoard1GoogleEventBusAdapter(NotifyBoard1 notifyBoard1) {
        this.notifyBoard1 = notifyBoard1;
    }

    @Subscribe
    public void whenWorkflow1Created(Workflow1Created workflow1Created) {
        notifyBoard1.whenWorkflow1Created(workflow1Created);
    }
}
