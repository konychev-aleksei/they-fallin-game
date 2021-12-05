package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.*;

public class MainMenuScreen implements Screen {
    final Drop game;
    OrthographicCamera camera;
    BitmapFont mfont, lfont;
    Viewport viewport;
    Rectangle b0;
    Vector3 touch;
    Texture next, arr, nexts, gr, bt, p1, p2, p3, p4, dropImage, dropImage2, player, player2, s1, s2, s3, i1, i2, i3, i4, i5, i6, i7, i8, white, prelogo;
    int x = 0, tm = 0, page = 1, y = -70, yy = -64, yt = -35, xx = 40, xxt = 40, t[] = {690, 640, 590, 540, 490, 440}, px = 0, py = 0, reg = 1, r = 0, u = 1, time = 0, pxx = 0, timeline = 0;
    float f = 0, ff = -0.2f, fff = 0.3f, k = 1, ffff = 0, ft = 0, ftt = 0;
    boolean was;

    Preferences prefs = Gdx.app.getPreferences("myprefs");

    public MainMenuScreen(final Drop game) {
        this.game = game;
        mfont = new BitmapFont(Gdx.files.internal("st1.fnt"));
        lfont = new BitmapFont(Gdx.files.internal("lst.fnt"));
        camera = new OrthographicCamera();
        touch = new Vector3();

        b0 = new Rectangle(70, 100, 340, 120);

        camera.setToOrtho(false, 480, 800);
        viewport = new FitViewport(480, 800, camera);

        camera.position.set(new Vector3(240, 400, 0));

        next = new Texture("next.png");
        nexts = new Texture("next_s.png");
        arr = new Texture("arr.png");
        gr = new Texture("gr.png");
        bt = new Texture("bt.png");

        p1 = new Texture("p1.png");
        p2 = new Texture("bst.png");
        p3 = new Texture("bst.png");

        p4 = new Texture("p4.png");

        dropImage = new Texture("plat/1.png");
        dropImage2 = new Texture("plat/2.png");
        player = new Texture("test.png");
        player2 = new Texture("testop.png");

        s1 = new Texture("ico/1.png");
        s2 = new Texture("ico/2.png");
        s3 = new Texture("ico/5.png");

        i1 = new Texture("items/5.png");
        i2 = new Texture("items/4.png");
        i3 = new Texture("items/6.png");
        i4 = new Texture("items/3.png");
        i5 = new Texture("items/1.png");
        i6 = new Texture("items/6.png");
        i7 = new Texture("items/7.png");
        i8 = new Texture("items/8.png");

        prelogo = new Texture("prelogo.jpg");
        white = new Texture("white.png");

        if(!prefs.contains("p_was")) prefs.putBoolean("p_was", false);
        prefs.flush();

        was = prefs.getBoolean("p_was");
    }

    private void drawBack() {
        if (page == 1) {
            game.batch.draw(dropImage, 150, t[0], 50, 10);
            game.batch.draw(dropImage, 280, t[1], 50, 10);

            game.batch.draw(dropImage, 150, t[2], 50, 10);
            game.batch.draw(dropImage, 280, t[3], 50, 10);

            game.batch.draw(dropImage, 150, t[4], 50, 10);
            game.batch.draw(dropImage, 280, t[5], 50, 10);


            game.batch.setColor(1, 1, 1, ft);
            game.batch.draw(player, 233 + px, 430 + py, 15, 15);
            game.batch.setColor(1, 1, 1, 1);

            if (Math.abs(px) > 27) reg *= -1;

            if (py < 250) {
                py++;
                px += reg;
            }
            else py = px = 0;
        }
        if (page == 2) {
            game.batch.setColor(1, 1, 1, ffff);
            if (u == 1) game.batch.draw(i1, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 2) game.batch.draw(i2, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 3) game.batch.draw(i3, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 4) game.batch.draw(i4, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 5) game.batch.draw(i5, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 6) game.batch.draw(i6, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 7) game.batch.draw(i7, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            if (u == 8) game.batch.draw(i8, 135 - xxt, 470 - xxt, 210 + xxt * 2, 210 + xxt * 2);
            game.batch.setColor(1, 1, 1, 1);
        }
        if (page == 3) {
            game.batch.setColor(1, 1, 1, fff);
            game.batch.draw(s1, 105 - xx / 2, 530 - xx / 2, 190 + xx, 190 + xx);
            game.batch.setColor(1, 1, 1, fff);
            game.batch.draw(s2, 195 - xx / 2, 530 - xx / 2, 190 + xx, 190 + xx);
            game.batch.setColor(1, 1, 1, fff);
            game.batch.draw(s3, 145 - xx / 2, 430 - xx / 2, 190 + xx, 190 + xx);
        }
        if (page == 4) {
            game.batch.draw(dropImage2, 150, t[0], 50, 10);
            game.batch.draw(dropImage2, 280, t[1], 50, 10);

            game.batch.draw(dropImage, 150, t[2], 50, 10);
            game.batch.draw(dropImage, 280, t[3], 50, 10);

            game.batch.draw(dropImage2, 150, t[4], 50, 10);
            game.batch.draw(dropImage, 280, t[5], 50, 10);


            game.batch.setColor(1, 1, 1, ft);
            game.batch.draw(player, 232 + px, 430 + py, 15, 15);

            game.batch.setColor(1, 1, 1, ftt);
            game.batch.draw(player2, 207 + pxx, 435 + py, 15, 15);


            game.batch.setColor(1, 1, 1, 1);



            if (Math.abs(px) > 27) reg *= -1;


            if (py < 250) {
                pxx++;
                py++;
                px += reg;
            }
            else py = px = pxx = 0;
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        if (was && timeline > 35) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        timeline++;

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(prelogo, 0, 0, 480, 800);

        if (!was && timeline > 35) {

            game.batch.draw(white, 0, 0, 480, 800);

            mfont.setColor(1, 1, 1, f);
            game.batch.setColor(1, 1, 1, f);

            game.batch.draw(nexts, 240 - 150, 80 + 50, 300, 300 / 5);
            game.batch.draw(next, 240 - 150, 80 + 60, 300, 300 / 5);
            if (page != 4 && page != 5) mfont.draw(game.batch, "NEXT", 210, 118 + 59);
            else mfont.draw(game.batch, "PLAY", 210, 118 + 59);
            game.batch.draw(arr, 240 + 110, 99 + 60, 23, 20);

            game.batch.setColor(1, 1, 1, ff);
            if (page == 1) game.batch.draw(p1, 240 - 125 - xx, 450 - xx, 250 + xx * 2, 250 + xx * 2);
            if (page == 4) game.batch.draw(p4, 240 - 125 - xx, 450 - xx, 250 + xx * 2, 250 + xx * 2);
            //if (page == 2) game.batch.draw(p2, 240 - 125 - xx, 450 - xx, 250 + xx * 2, 250 + xx * 2);
            //if (page == 3) game.batch.draw(p3, 240 - 125 - xx, 450 - xx, 250 + xx * 2, 250 + xx * 2);

            drawBack();

            mfont.setColor(1, 1, 1, 1);
            game.batch.setColor(1, 1, 1, 0.8f);
            game.batch.draw(gr, 240 + 7, 60 + yy, 10, 10);
            game.batch.draw(gr, 240 + 7 + 10 + 14, 60 + y, 10, 10);
            game.batch.draw(gr, 240 - 7 - 10, 60 + yy, 10, 10);
            game.batch.draw(gr, 240 - 7 - 10 - 14 - 10, 60 + y, 10, 10);
            game.batch.setColor(1, 1, 1, 1);

            game.batch.draw(bt, 240 - 7 - 10 - 14 - 10 + x, 60 + y, 10, 10);

            lfont.setColor(0, 0, 0, f - 0.3f);
            if (page == 1) lfont.draw(game.batch, "Avoid obstacles", 159, 118 + 160 + yt);
            if (page == 2) lfont.draw(game.batch, "Use improvements", 150, 118 + 160 + yt);
            if (page == 3) lfont.draw(game.batch, "Discover new locations", 129, 118 + 160 + yt);
            if (page == 4) lfont.draw(game.batch, "Play with friends", 161, 118 + 160 + yt);
            if (page == 5) lfont.draw(game.batch, "LET'S GO!", 190, 118 + 160 + yt);


            //game.batch.draw(p1, 240 - 1, 0, 2, 800);
        }

        game.batch.end();

        if (!was && timeline > 35) {
            r++;


            if (page == 2 && x < 24) x++;
            if (page == 3 && x < 48) x++;
            if (page == 4 && x < 72) x++;

            if (tm != 0) tm++;
            if (tm >= 40) tm = 0;

            if (yt < 0) yt++;

            if (xx > 0) xx--;
            if (xxt > 0) xxt--;

            if (ff < 1) ff += 0.01f;
            if (f < 1) f += 0.01f;
            if (y < 0) y += 3;
            if (yy < 0) yy += 3;

            fff += 0.003f * k;
            if (fff >= 1) k = -1;
            if (fff <= 0.6f) k = 1;

            if (page == 2) time++;
            if (time > 880) time = 0;
            if (time % 80 == 0) {
                u++;
                ffff = 0.2f;
                xxt = 35;
                if (u > 8) u = 1;
            }
            if (ffff < 1) ffff += 0.007f;

            if (ftt <= 1 && py < 50 && page == 4) ftt += 0.07f;
            if (py > 90 && page == 4 && ftt > 0) ftt -= 0.05f;
            if (ft < 1) ft += 0.01f;
            if (py == 245) ft = 0.3f;

            if (page == 5 && f > 0.5f) {

                game.setScreen(new GameScreen(game));
                dispose();
            }

            if (Gdx.input.justTouched()) {
                viewport.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

                if (b0.contains(touch.x, touch.y) && tm == 0) {
                    tm = 1;
                    page++;
                    px = py = 0;
                    yt = -35;
                    f = 0.3f;
                    xx = 35;
                    ff = -0.2f;
                }
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
