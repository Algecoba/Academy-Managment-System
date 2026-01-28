
module com.acb.ams {
    // Requerimientos de JavaFX

    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;

    // Bibliotecas externas adicionales
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    // Requerimientos de bases de datos
    requires transitive java.sql;

    // requires mysql.connector.j; // Descomentar si se usa MySQL

    // Requerimientos adicionales para íconos y fuentes
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.base;
    requires java.desktop;

    // Exportación de paquetes necesarios
    exports com.acb.ams;
    exports com.acb.ams.Controllers.Admin;
    exports com.acb.ams.Controllers.Professor;
    exports com.acb.ams.Controllers.Student;
    exports com.acb.ams.Controllers.Login;
    exports com.acb.ams.Views;
    exports com.acb.ams.Models;
    exports com.acb.ams.Data;

    // Apertura para la reflexión (acceso por parte de JavaFX)
    opens com.acb.ams to javafx.fxml;
    opens com.acb.ams.Controllers.Admin to javafx.fxml;
    opens com.acb.ams.Controllers.Professor to javafx.fxml;
    opens com.acb.ams.Controllers.Student to javafx.fxml;
    opens com.acb.ams.Controllers.Login to javafx.fxml;
    opens com.acb.ams.Data;
    opens com.acb.ams.Models;
}
