package com.mygdx.game;

import java.lang.String;
import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;

public class BackStore extends ApplicationAdapter implements Screen {
    final Drop game;
    SpriteBatch batch = new SpriteBatch();
    OrthographicCamera camera;
    Texture dropImage, coinsign, closepng, menubg, dark, tick, lockbg, shadow;
    Texture arr, gr, wt, cl, ad;

    Viewport viewport;
    Vector3 touch;
    Texture[] ico = new Texture[11];
    BitmapFont font = new BitmapFont(Gdx.files.internal("text.fnt"));
    BitmapFont tfont = new BitmapFont(Gdx.files.internal("ttl.fnt"));

    Sound apply, click;
    Rectangle rb, lb, cls, ap;

    String pr[][] = {{"SCORE EXACTLY 123 POINTS"}, {"SCORE EXACTLY 50 POINTS"}, {"IN ONE GAME", "COLLECT 1000 OR MORE COINS"}, {"WITHOUT TOUCHING", "SPEND 13 SECONDS"}, {"IN ONE GAME", "USE 5 BUBBLE PROTECTIONS"}, {"ACQUIRE 5 ITEMS"}, {"IN HARDCORE MODE", "SCORE 7K POINTS"}, {"", "PLAY 100 GAMES"}, {"IN MULTIPLAYER MODE", "SCORE 150 OR MORE POINTS"}};
    int mpn[][] = {{-40}, {-40}, {41, -53}, {1, 6}, {43, -32}, {23}, {2, 22}, {26, 0}, {-10, -42}};
    int pn[] = {1, 1, 2, 2, 2, 1, 2, 2, 2};

    String tl[] = {"SUMMER SUNSET", "WINTER", "VOLCANO", "BEACH", "SPACE", "WILD WEST", "AMUSEMENT PARK", "NIGHT CITY", "ASIAN VILLAGE"};
    int ktl[] = {-75, 1, -19, 12, 14, -27, -89, -25, -57};

    String bmask;
    int page = 0, t = 0, tm = 0, type = 0, c = 30, cc = -15, cur = 7, soundon = 0, pl = 0;
    float f = 0.f, ac = 0.7f, acc = 1, x = 0.f;
    boolean closeit = false, bt[] = {true, false, false, false, false, false, false, false, false};

    Preferences prefs = Gdx.app.getPreferences("myprefs");

    public BackStore(final Drop game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new FitViewport(480, 800, camera);

        touch = new Vector3();

        dropImage = new Texture("potion.png");
        coinsign =  new Texture("coin.png");
        closepng =  new Texture("close.png");
        dark =  new Texture("red.png");
        menubg =  new Texture("bst.png");
        ad =  new Texture("ad.png");
        lockbg =  new Texture("back/lock.png");
        shadow = new Texture("ico/shadow.png");

        apply = Gdx.audio.newSound(Gdx.files.internal("snd/apply.mp3"));
        click = Gdx.audio.newSound(Gdx.files.internal("snd/click.mp3"));

        ico[0] =  new Texture("ico/1.png");
        ico[1] =  new Texture("ico/2.png");
        ico[2] =  new Texture("ico/3.png");
        ico[3] =  new Texture("ico/7.png");
        ico[4] =  new Texture("ico/4.png");
        ico[5] =  new Texture("ico/6.png");
        ico[6] =  new Texture("ico/8.png");
        ico[7] =  new Texture("ico/5.png");
        ico[8] =  new Texture("ico/9.png");

        ico[10] =  new Texture("ico/0.png");

        tick = new Texture("tick.png");

        cl = new Texture("cl.png");
        arr = new Texture("arr.png");

        gr = new Texture("gr.png");
        wt = new Texture("wt.png");

        pl = prefs.getInteger("p_pl");
        bmask = prefs.getString("p_b");
        loadbMask();
        cur = prefs.getInteger("p_bg");
        soundon = prefs.getInteger("p_snd");

        ap = new Rectangle(140, 175, 200, 60);
        cls = new Rectangle(330, 660, 170, 170);
        rb = new Rectangle(370, 360, 115, 115);
        lb = new Rectangle(0, 360, 115, 115);
    }

    private void loadbMask() {
        for (int i = 0; i < 9; i++) if (bmask.charAt(i) == '1') bt[i] = true;
    }

    private void savebMask() {
        bmask = "";
        for (int i = 0; i < 9; i++) if (bt[i] == true) bmask += "1";
        else bmask += "0";
        prefs.putString("p_b", bmask);
    }

    private void drawTask() {
        if (pn[page] == 1) font.draw(batch, "" + pr[page][0], 120 + mpn[page][0], 175 + c + 67 - 50);
        else {
            if (page != 7) {
                font.draw(batch, "" + pr[page][0], 120 + mpn[page][0], 175 + c + 57 - 50);
                font.draw(batch, "" + pr[page][1], 120 + mpn[page][1], 175 + c + 116 - 50);
            } else {
                font.draw(batch, "" + pr[page][1], 120 + mpn[page][0], 175 + c + 116 - 50);
                font.draw(batch, "( " + pl + " / 111)", 192, 175 + c + 57 - 50);
            }
        }
    }

    private void drawItems() {
        batch.setColor(1,1,1, 1);


        batch.setColor(1,1,1, x - 0.2f);
        font.setColor(1,1,1, x);

        if (bt[page] == false) batch.draw(lockbg, 240 - 85 - 25, 335 - 25 + c, 170 + 50, 170 + 50);
        else {
            batch.draw(shadow, 242 - 85 - 28 - 35, 335 - 28 + c - 60 - 25, 170 + 56 + 50, 170 + 56 + 50);
            batch.draw(ico[page], 240 - 85 - 25, 335 - 25 + c, 170 + 50, 170 + 50);
        }


        tfont.draw(batch, "" + tl[page], 178 + ktl[page], 680);


        if (bt[page] == false) drawTask();
        if (bt[page] == true && cur == page) {
            batch.draw(tick, 176, 198, 24, 24);
            font.draw(batch, "APPLIED", 205, 218);
        }
        if (bt[page] == true && cur != page) {
            batch.setColor(1, 1, 1, 0.15f);
            batch.draw(dark, 150, 185, 180, 40);
            batch.setColor(1, 1, 1, x);
            batch.draw(cl, 150, 190, 180, 40);
            font.draw(batch, "APPLY", 204, 218);
        }

        //batch.draw(dark, 240 - 85 - 5, 0, 2, 800);
        //batch.draw(dark, 240 - 85 - 5 + 170 + 10, 0, 2, 800);

        if (Gdx.input.justTouched() && cur != page && tm == 0) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (ap.contains(touch.x, touch.y)) {
                cur = page;
                if (soundon == 1) apply.play(1.5f);
                x = 0.4f;
                tm = 1;
            }
        }

        //bt[4] = bt[5] = true;

        font.setColor(1,1,1, 1);
        batch.setColor(1,1,1, 1);
        if (x < 1.f) x += 0.03f;

        batch.draw(gr, 172, 110 + cc, 8, 8);
        batch.draw(gr, 300, 110 + cc, 8, 8);

        batch.draw(gr, 252, 110 + cc, 8, 8);
        batch.draw(gr, 236, 110 + cc, 8, 8);
        batch.draw(gr, 220, 110 + cc, 8, 8);

        batch.draw(gr, 204, 110 + cc, 8, 8);
        batch.draw(gr, 188, 110 + cc, 8, 8);

        batch.draw(gr, 268, 110 + cc, 8, 8);
        batch.draw(gr, 284, 110 + cc, 8, 8);

        if (Math.abs(page) % 9 == 0) batch.draw(wt, 172, 110 + cc, 8, 8);

        if (Math.abs(page) % 9 == 1) batch.draw(wt, 188, 110 + cc, 8, 8);
        if (Math.abs(page) % 9 == 2) batch.draw(wt, 204, 110 + cc, 8, 8);

        if (Math.abs(page) % 9 == 3) batch.draw(wt, 220, 110 + cc, 8, 8);
        if (Math.abs(page) % 9 == 4) batch.draw(wt, 236, 110 + cc, 8, 8);
        if (Math.abs(page) % 9 == 5) batch.draw(wt, 252, 110 + cc, 8, 8);

        if (Math.abs(page) % 9 == 6) batch.draw(wt, 268, 110 + cc, 8, 8);
        if (Math.abs(page) % 9 == 7) batch.draw(wt, 284, 110 + cc, 8, 8);

        if (Math.abs(page) % 9 == 8) batch.draw(wt, 300, 110 + cc, 8, 8);


        if (ac >= 0.8f) acc = -1;
        if (ac <= 0.6f) acc = 1;
        ac += 0.01f * acc;

        if (Gdx.input.justTouched() && tm == 0) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (lb.contains(touch.x, touch.y)) {
                page--;
                if (page == -1) page = 8;
                tm = 1;
                x = 0.3f;
                if (soundon == 1) click.play(0.05f);
            }
            if (rb.contains(touch.x, touch.y)) {
                page++;
                if (page == 9) page = 0;
                tm = 1;
                x = 0.3f;
                if (soundon == 1) click.play(0.05f);
            }
        }
        if (tm > 0) tm++;
        if (tm == 9) tm = 0;


        batch.setColor(1,1,1, ac);
        batch.draw(arr, 64, 390 + c, -50, 50);

        batch.setColor(1,1,1, 1.4f - ac);
        batch.draw(arr, 480 - 64, 390 + c, 50, 50);

        batch.setColor(1,1,1, 1);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(menubg, 0, 0, 480, 800);
        batch.setColor(1,1,1, 0.3f);
        batch.draw(dark, -120, -120, 670, 1010);
        batch.setColor(1,1,1, 1);

        batch.setColor(1,1,1, 0.6f);
        batch.draw(closepng, 450, 770, 23, 23);

        drawItems();

        batch.setColor(1,1,1, f);
        batch.draw(dark, -120, -120, 670, 1010);
        batch.setColor(1,1,1, 1);
        batch.end();

        if (Gdx.input.justTouched() && tm == 0) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (cls.contains(touch.x, touch.y)) closeit = true;
        }

        if (closeit) {
            f += 0.1f;
            if (f > 1) {
                savebMask();
                prefs.putInteger("p_bg", cur);
                prefs.flush();
                game.setScreen(new GameScreen(game));
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
