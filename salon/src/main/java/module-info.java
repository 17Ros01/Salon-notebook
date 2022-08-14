module com.yokish.salon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.yokish.salon to javafx.fxml;
    exports com.yokish.salon;
    exports com.yokish.salon.utils;
    opens com.yokish.salon.utils to javafx.fxml;
    exports com.yokish.salon.dao.sqlCommands;
    opens com.yokish.salon.dao.sqlCommands to javafx.fxml;
    exports com.yokish.salon.service;
    opens com.yokish.salon.service to javafx.fxml;
    exports com.yokish.salon.controllers;
    opens com.yokish.salon.controllers to javafx.fxml;
}