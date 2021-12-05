package com.mygdx.game;

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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.String;

public class Store extends ApplicationAdapter implements Screen {
    final Drop game;
    SpriteBatch batch = new SpriteBatch();
    OrthographicCamera camera;
    Texture dropImage, coinsign, closepng, menubg, dark, tick, video, tap;
    Texture arr, gr, wt, cl, ad;
    Texture[][] ico = new Texture[11][3];

    Vector3 touch;
    Viewport viewport;
    TextureRegion io;
    Rectangle rb, lb, cls, ap, adv;

    Sound snddrop, click, apply;

    BitmapFont font = new BitmapFont(Gdx.files.internal("text.fnt"));
    BitmapFont lfont = new BitmapFont(Gdx.files.internal("lil.fnt"));

    Preferences prefs = Gdx.app.getPreferences("myprefs");

    String tl[] = {"BOOSTER", "SPIKES PROTECTION", "BUBBLE PROTECTION", "SECOND CHANCE", "MONEY BOX", "TELEPORT", "CONTROLLER", "DOUBLE SECOND CHANCE", "HARDCORE MODE", "COIN MAGNET", "PORTAL"};
    int tp[] = {-13, -77, -78, -70, -34, -18, -40, -116, -69, -49, -6};

    int p[] = {1, 2, 0, 3, 9, 7, 5, 6, 10, 4, 8};
    int pp[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    int coins, page = 1, pg = 0, t = 0, tm = 0, type = 0, c = 30, cc = -15, cur = 10, soundon, tv = 0, tr = 1;
    //int pr[] = {7000, 1000, 5000, 10000, 150000, 75000, 80000, 50000, 500000, 45000, 90000};
    int pr[] = {100, 100, 100, 100, 100, 1000, 100, 100, 100, 100, 100};

    String bmask;
    float f = 0.f, ac = 0.7f, acc = 1, x = 0.f, r = 0.1f;
    boolean closeit = false, bt[] = {false, false, false, false, false, false, false, false, false, false, false};

    public Store(final Drop game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new FitViewport(480, 800, camera);

        dropImage = new Texture("potion.png");
        coinsign =  new Texture("coin.png");
        closepng =  new Texture("close.png");
        dark =  new Texture("red.png");
        menubg =  new Texture("storebg.png");
        ad =  new Texture("ad.png");

        snddrop = Gdx.audio.newSound(Gdx.files.internal("snd/coin.mp3"));
        click = Gdx.audio.newSound(Gdx.files.internal("snd/click.mp3"));
        apply = Gdx.audio.newSound(Gdx.files.internal("snd/apply.mp3"));

        ico[0][0] =  new Texture("items/1.png");
        ico[1][0] =  new Texture("items/2.png");
        ico[2][0] =  new Texture("items/3.png");
        ico[3][0] =  new Texture("items/4.png");
        ico[4][0] =  new Texture("items/5.png");
        ico[5][0] =  new Texture("items/6.png");
        ico[6][0] =  new Texture("items/7.png");
        ico[7][0] =  new Texture("items/8.png");
        ico[8][0] =  new Texture("items/9.png");
        ico[9][0] =  new Texture("items/11.png");
        ico[10][0] =  new Texture("items/12.png");

        io = new TextureRegion(ico[5][0]);

        tick = new Texture("tick.png");

        cl = new Texture("cl.png");
        arr = new Texture("arr.png");

        gr = new Texture("gr.png");
        wt = new Texture("wt.png");

        video = new Texture("video.png");
        tap = new Texture("tap.png");

        //if(!prefs.contains("p_bm")) prefs.putString("p_bm", "000000000");
        //prefs.flush();

        coins = prefs.getInteger("p_coins");
        bmask = prefs.getString("p_bm");
        loadbMask();
        cur = prefs.getInteger("p_md");
        if (cur < 10 && cur >= 0) cur = pp[cur];
        else if (cur == 10) cur = -1;
        else cur = pp[cur - 2];

        soundon = prefs.getInteger("p_snd");

        touch = new Vector3();

        ap = new Rectangle(130, 165, 220, 90);

        adv = new Rectangle(385, 0, 100, 100);
        cls = new Rectangle(330, 660, 170, 170);
        rb = new Rectangle(370, 360, 115, 115);
        lb = new Rectangle(0, 360, 115, 115);
    }

    private void loadbMask() {
        for (int i = 0; i < 11; i++) if (bmask.charAt(i) == '1') bt[i] = true;
    }

    private void savebMask() {
        bmask = "";
        for (int i = 0; i < 11; i++) if (bt[i] == true) bmask += "1";
        else bmask += "0";
        prefs.putString("p_bm", bmask);
    }

    private void drawItems() {
        batch.setColor(1,1,1, 1);


        batch.setColor(1,1,1, x - 0.1f);
        font.setColor(1,1,1, x);

        if (page == 7) batch.draw(ico[page][type], 240 - 85 - 5 - 30, 335 - 5 + c - 30, 170 + 10 + 60, 170 + 10 + 60);
        else if (page != 5) batch.draw(ico[page][type], 240 - 85 - 5, 335 - 5 + c, 170 + 10, 170 + 10);
        else batch.draw(io, 240 - 85 - 5 - 20, 335 - 5 + c - 20, 110, 110, 180 + 40, 180 + 40, 1, 1, r, false);


        if (page == 5) r++;
        else r = 0;

        if (bt[page] == true) {
            font.setColor(1, 1, 1, 0.1f);
            batch.setColor(1, 1, 1, 0.1f);
        }
        batch.draw(coinsign, 195, 291 + c - 50, 23, 23);
        font.draw(batch, "" + pr[page], 225, 310 + c - 50);


        font.setColor(1, 1, 1, 1);
        batch.setColor(1, 1, 1, 1);


        if (page == 6) {
            batch.draw(tap, 200 + tv, 395, 65, 65);

            tv += tr;
            if (tv > 50 && tr == 1) tr = -1;
            if (tv < -40 && tr == -1) tr = 1;
        }


        font.setColor(1, 1, 1, x);
        font.draw(batch, "" + tl[page], 205 + tp[page], 650);
        font.setColor(1, 1, 1, 1);


        if (bt[page] == false && cur != page && coins < pr[page]) {
            batch.setColor(1, 1, 1, 0.15f);
            batch.draw(dark, 170, 190, 140, 40);
            font.setColor(1, 1, 1, x - 0.6f);
            font.draw(batch, "BUY", 221, 218);
            font.setColor(1, 1, 1, 1);
        }
        if (bt[page] == false && cur != page && coins >= pr[page]) {
            batch.setColor(1, 1, 1, 0.15f);
            batch.draw(dark, 170, 185, 140, 40);
            batch.setColor(1, 1, 1, x);
            batch.draw(cl, 170, 190, 140, 40);
            font.draw(batch, "BUY", 221, 218);
        }
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

        if (Gdx.input.justTouched()) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (ap.contains(touch.x, touch.y)) {
                if (bt[page] == false && cur != page && coins >= pr[page] && tm == 0) {
                    coins -= pr[page];
                    if (soundon == 1) snddrop.play();
                    bt[page] = true;
                    x = 0.4f;
                    tm = 1;
                }
                if (bt[page] == true && cur != page && tm == 0) {
                    if (soundon == 1) apply.play();
                    cur = page;
                    x = 0.4f;
                    tm = 1;
                }
            }
        }



        font.setColor(1,1,1, 1);
        batch.setColor(1,1,1, 1);
        if (x < 1.f) x += 0.03f;

        int tt = 0;

        batch.draw(gr, 156 - tt, 110 + cc, 8, 8);

        batch.draw(gr, 252 - tt, 110 + cc, 8, 8);
        batch.draw(gr, 236 - tt, 110 + cc, 8, 8);
        batch.draw(gr, 220 - tt, 110 + cc, 8, 8);

        batch.draw(gr, 204 - tt, 110 + cc, 8, 8);
        batch.draw(gr, 188 - tt, 110 + cc, 8, 8);

        batch.draw(gr, 268 - tt, 110 + cc, 8, 8);
        batch.draw(gr, 284 - tt, 110 + cc, 8, 8);

        batch.draw(gr, 172 - tt, 110 + cc, 8, 8);
        batch.draw(gr, 300 - tt, 110 + cc, 8, 8);

        batch.draw(gr, 316 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 0) batch.draw(wt, 156 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 1) batch.draw(wt, 172 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 2) batch.draw(wt, 188 - tt, 110 + cc, 8, 8);
        if (Math.abs(pg) % 11 == 3) batch.draw(wt, 204 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 4) batch.draw(wt, 220 - tt, 110 + cc, 8, 8);
        if (Math.abs(pg) % 11 == 5) batch.draw(wt, 236 - tt, 110 + cc, 8, 8);
        if (Math.abs(pg) % 11 == 6) batch.draw(wt, 252 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 7) batch.draw(wt, 268 - tt, 110 + cc, 8, 8);
        if (Math.abs(pg) % 11 == 8) batch.draw(wt, 284 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 9) batch.draw(wt, 300 - tt, 110 + cc, 8, 8);

        if (Math.abs(pg) % 11 == 10) batch.draw(wt, 316 - tt, 110 + cc, 8, 8);

        if (ac >= 0.8f) acc = -1;
        if (ac <= 0.6f) acc = 1;
        ac += 0.01f * acc;

        if (Gdx.input.justTouched() && tm == 0) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (lb.contains(touch.x, touch.y)) {
                if (soundon == 1) click.play(0.05f);
                pg--;
                if (pg == -1) pg = 10;
                page = p[pg];
                tm = 1;
                x = 0.3f;
            }
            if (rb.contains(touch.x, touch.y)) {
                if (soundon == 1) click.play(0.05f);
                pg++;
                if (pg == 11) pg = 0;
                page = p[pg];
                tm = 1;
                x = 0.3f;
            }
        }
        if (tm > 0) tm++;
        if (tm == 9) tm = 0;


        batch.setColor(1,1,1, ac);
        batch.draw(arr, 64, 390 + c, -50, 50);

        batch.setColor(1,1,1, 1.4f - ac);
        batch.draw(arr, 480 - 64, 390 + c, 50, 50);

        batch.setColor(1,1,1, 1);


        lfont.setColor(1,1,1, 0.7f);
        batch.draw(video, 399, 13, 23, 20);
        lfont.setColor(1,1,1, 1);
        batch.draw(coinsign, 428, 18, 10, 10);
        lfont.setColor(1,0.75f,0, 1);
        lfont.draw(batch, "    100", 425, 29);
        lfont.setColor(1,1,1, 1);

        if (Gdx.input.justTouched() && tm == 0) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (adv.contains(touch.x, touch.y)) {
                coins += 100;
                tm = 1;
            }
           //ad here
        }

        //batch.draw(dark, 240 - 85 - 5, 0, 2, 800);
        //batch.draw(dark, 240 - 85 - 5 + 170 + 10, 0, 2, 800);
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
        batch.setColor(1,1,1, 0.4f);
        batch.draw(dark, -120, -120, 670, 1010);
        batch.setColor(1,1,1, 1);

        batch.draw(coinsign, 10, 770, 23, 23);
        batch.setColor(1,1,1, 0.6f);
        batch.draw(closepng, 450, 770, 23, 23);

        font.setColor(1,0.75f,0, 1);
        font.draw(batch, "" + coins, 40, 789);
        //font.setColor(1,1,1, 1);

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
                prefs.putInteger("p_coins", coins);
                if (cur < 9 & cur > -1) prefs.putInteger("p_md", pp[cur]);
                else if (cur == -1) prefs.putInteger("p_md", 10);
                else prefs.putInteger("p_md", cur + 2);
                savebMask();
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
