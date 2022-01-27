module com.strwatcher.noder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.swing;
    requires opencv;
    requires com.google.gson;

    opens com.strwatcher.noder to javafx.fxml, com.google.gson;
    exports com.strwatcher.noder;
    opens com.strwatcher.noder.base to javafx.fxml, com.google.gson;
    exports com.strwatcher.noder.base;
    opens com.strwatcher.noder.nodes to javafx.fxml, com.google.gson;
    exports com.strwatcher.noder.nodes;
    opens com.strwatcher.noder.nodes.edit_nodes to javafx.fxml, com.google.gson;
    opens com.strwatcher.noder.nodes.filter_nodes to javafx.fxml, com.google.gson;
    opens com.strwatcher.noder.serialization to com.google.gson;
}