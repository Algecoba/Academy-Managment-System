module com.acb.ams {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    //Requerimientos adicionales
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires mysql.connector.j;

    opens com.acb.ams to javafx.fxml;
    exports com.acb.ams;
    //Export all to our folders
    exports com.acb.ams.Controllers;
    exports com.acb.ams.Controllers.Admin;
    exports com.acb.ams.Controllers.Professor;
    exports com.acb.ams.Controllers.Student;
    exports com.acb.ams.Views;
    exports com.acb.ams.Models;
}