module edu.alma.teamleft {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jooq;


    opens edu.alma.teamleft to javafx.fxml;
    exports edu.alma.teamleft;
    exports edu.alma.teamleft.tables.records to org.jooq;
}