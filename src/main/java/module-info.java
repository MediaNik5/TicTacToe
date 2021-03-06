module org.medianik.tictactoe {
    requires javafx.controls;
    requires static lombok;
    exports org.medianik.tictactoe;
    exports org.medianik.tictactoe.player;
    exports org.medianik.tictactoe.util;
    exports org.medianik.tictactoe.jfxhelper;
    exports org.medianik.tictactoe.gameobject;
}