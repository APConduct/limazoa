package org.plashub.limazoa;
import static com.raylib.Colors.LIGHTGRAY;
import static com.raylib.Colors.RAYWHITE;
import static com.raylib.Raylib.*;

public class Main {

    public static void main(String[] args) {
        System.out.print("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

            // Initialize window
        InitWindow(800, 450, "raylib [core] example - basic window");
        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second
        while (!WindowShouldClose()) {
            BeginDrawing();
            EndDrawing();
            // Detect window close button or ESC key
        }


    }

}
