module org.medianik.tictactoe {
    requires org.jetbrains.annotations;
    requires static lombok;
    requires javafx.controls;
    exports org.medianik.tictactoe;
    exports org.medianik.tictactoe.player;
    exports org.medianik.tictactoe.util;
    exports org.medianik.tictactoe.jfxhelper;
    exports org.medianik.tictactoe.gameobject;
}