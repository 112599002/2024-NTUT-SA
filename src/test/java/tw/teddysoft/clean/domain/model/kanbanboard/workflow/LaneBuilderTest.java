package tw.teddysoft.clean.domain.model.kanbanboard.workflow;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class LaneBuilderTest {

    @Test
    public void default_lane_created_by_lanebuilder_is_stage() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .build();

        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
        assertTrue(lane instanceof Stage);
        assertEquals(0, lane.getWipLimit());
        assertEquals("Backlog", lane.getName());
        assertEquals("000-1234", lane.getWorkflowId());
        assertThat(lane.getDomainEvents().size()).isEqualTo(0);
    }

    @Test
    public void create_stage_should_not_generate_domainevent() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .swimlane()
                .stage()
                .wipLimit(5)
                .build();

        assertThat(lane.getDomainEvents().size()).isEqualTo(0);
        assertEquals(5, lane.getWipLimit());
        assertEquals(LaneOrientation.VERTICAL, lane.getOrientation());
    }

    @Test
    public void create_swimlane_should_not_generate_domainevent() {
        Lane lane = LaneBuilder.getInstance()
                .name("Backlog")
                .workflowId("000-1234")
                .stage()
                .swimlane()
                .wipLimit(1)
                .build();

        assertEquals(LaneOrientation.HORIZONTAL, lane.getOrientation());
        assertEquals(1, lane.getWipLimit());
        assertThat(lane.getDomainEvents().size()).isEqualTo(0);
    }

}
