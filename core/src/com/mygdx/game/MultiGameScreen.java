package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.*;

public class MultiGameScreen extends ApplicationAdapter implements Screen {
    final Drop game;

    Vector3 touchpos;
    OrthographicCamera camera;
    Sound snddrop, danger, mg, acd, bbl, congrats, blast;
    Texture dropImage, circleImage, retryBtn, watchadBtn, gameover, coinsign, watchad, retryBtn_, watchadBtn_, sndon, sndoff, maps, cir, mpl, bubble, spike, magsign, energy, potionicon, laser, we1, we2;
    Texture bgw, bgs, bgn, bgbb, bgbf, bgj, wt, green, warn, tick, bgs_m, boat, btide, ftide, star, space, comet, bgn1, bgwest, millww, amparkb, amparkf, amparka, asiab, asiaf, ballon, dropDark, circleImage2, vvdropImage;
    Texture btip, rtip, vdropImage;
    Texture[] mode = new Texture[9];
    Music sndback;
    Rectangle bucket, sucket;
    Viewport viewport;

    Rectangle br, ba;
    Vector3 touch;

    Array<Rectangle> raindrops, coindrops, magnetdrops, accdrops, bubdrops, potiondrops, pigdrops, bulletdrops;
    BitmapFont font = new BitmapFont(Gdx.files.internal("text.fnt"));
    BitmapFont wfont = new BitmapFont(Gdx.files.internal("win.fnt"));
    BitmapFont score = new BitmapFont();
    String bmask, bask;
    long lastDropTime, lastTouchTime, lastChangeSound, lastSecond, lastcoinDropTime, lastitemDropTime;
    boolean allowTouch = true, retry = false, readytoupdate = false, ad = false, gets = false, snd = true, dodarken = false, allowTouchd = false, showup = false, bb[] = {true, false, false, false, false, false, false, false, false}, wonred = false, reload = false;
    int rightMoving1 = 1, rightMoving2 = 1, unpaused = 1, x = -40, k, readytoplay = 0, xx = -20, _xx, speed = 0, ss = 0, eg = 1, eg_t = 0, acceleration = 0, del = 0, invincible = 0, invincible2 = 0, inv_t = 0, moneygang = 0, gametype = 21, fi = 0, pfi = 0, dx = -30, gt2s = 0, gt3s = 0, gt4s = 0, gt5s = 0, magnet = 0, magnet2 = 0, gtt = 1;
    long c = 0, r = 0, hm = 0, mp = 0;
    float l = 0.3f, ll = 0.f, f = 0.f, tts = 0.4f, ec = 1, mb = 0.3f, plm = 1.f, ilm = 0.f, ini = 1.f, lll = 0.f, _lll = 1, vvv = 0.f, yu = 0.f, nnn = 0, tttt = 0, rr = -95, ii = 0, uy = 1, rpox = 0, rpoy = 0;
    int md = 10, attempts = 0, cnt = 100, inb = 0, gt6s = 0, gt7s = 0, gt8s = 0, tf = 0, points = 0, coins = 0, highscore = 0, rdr = 0, ccn = 0, bc = 0, it5 = 0, bg, soundon = 1, potion = 0, tiff = 0, rx = 0, ry = 0, rio = 0, pl = 0, utrx = -20, utry = 0, ftype, rerr = 0, gt1 = 0;
    float oi = 0, yoi = 1, lx = -10, lt = 0, gametime = 0;

    TextureRegion io,dropImaget, mill, mill_w, amparkaa;

    public static final int PL_WIDTH = 60;
    public static final int PL_HEIGHT = 15;

    Preferences prefs = Gdx.app.getPreferences("myprefs");

    public void showTip() {
        if (oi >= 0.7f) yoi = -1;
        if (oi <= 0) {
            yoi = 1;
            rerr++;
        }
        oi += (0.02 * yoi);

        game.batch.setColor(1, 1, 1, 0);


        if (rerr < 2) game.batch.setColor(1, 1, 1, oi);
        game.batch.draw(rtip, 30, 130, 180, 180);

        if (rerr < 2) game.batch.setColor(1, 1, 1, oi);
        game.batch.draw(btip, 270, 130, 180, 180);

        if (rerr >= 2) readytoplay = 1;

        game.batch.setColor(1, 1, 1, 1);
    }


    public void controlItems() {
        int kd = 0; if (md == 0) kd = 2100;

        if (acceleration != 0) acceleration++;
        if (md == 0 && acceleration != 0) acceleration += 5;
        if (acceleration > 500 + kd) acceleration = 0;

        if (magnet != 0) magnet++;
        if (magnet > 350) magnet = 0;

        if (magnet2 != 0) magnet2++;
        if (magnet2 > 350) magnet2 = 0;
    }

    public void drawMode() {
        if (unpaused == 0 || md == 10) return;
        if (md != 5) game.batch.draw(mode[md], 340, 767, 30, 28);
        else game.batch.draw(io, 340, 767, 15, 15, 30, 28, 1, 1, r);
        r++;
    }

    public void drawItems() {
        lll += (0.035f * _lll);
        if (lll <= 0.3f) _lll = 1;
        if (lll >= 1.f) _lll = -1;

        game.batch.setColor(1,1,1, lll);
        for (Rectangle met : magnetdrops) {
            game.batch.draw(magsign, met.x + 5, met.y - 5, 28, 28);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }
        for (Rectangle acct : accdrops) {
            game.batch.draw(energy, acct.x + 5, acct.y - 5, 28, 28);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }
        for (Rectangle yiu : bubdrops) {
            game.batch.draw(bubble, yiu.x + 5, yiu.y - 5, 28, 28);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }
        for (Rectangle yu : potiondrops) {
            game.batch.draw(potionicon, yu.x + 5, yu.y - 5, 30, 30);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }
        for (Rectangle mt : pigdrops) {
            game.batch.draw(mode[4], mt.x + 5, mt.y - 5, 28, 28);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }
        game.batch.setColor(1,1,1, 1);
        controlItems();
    }


    public void spawnSpike() {
        if (unpaused == 1) gt3s++;
        if (gt3s == 3 && soundon == 1) danger.play();
        if (gt3s < 60) return;
        if (gt3s < 20 + 60 && dx < -10) dx++;
        if (gt3s > 420 + 60 && dx > -29) dx--;
        if (gt3s > 430 + 60) gametype = 1;

        game.batch.setColor(1,1,1, 0.7f);
        for (int i = 0; i < 34; i++) {
            game.batch.draw(spike, 0 + dx, 0 + i * 30, 30, 30);
            game.batch.draw(spike, 480 - dx, 0 + i * 30, -30, 30);
        }
        if (md == 1) {
            game.batch.draw(dropDark, 25 + dx, 0, 5, 800);
            game.batch.draw(dropDark, 450 - dx, 0, 5, 800);
        }
        game.batch.setColor(1,1,1, 1);
    }

    public void getGameType() {
        if ((unpaused == 0 || readytoplay == 0) && gametype != 3 && gametype != 9) return;
        if (gametype == 1 && ss % 30 == 0 && md != 8 && tf == 0) {
            Random rr = new Random();
            int y = rr.nextInt(31);
            y = 11;
            if (md != 8) {
                if (y == 1) {
                    gametype = 2;
                    tiff = 0;
                }
                if (y == 2) gametype = 3;
                if (y == 3) gametype = 4;
                if (y == 4) gametype = 5;
                if (y == 5) gametype = 6;
                if (y == 6) tf = 1;
                else tf = 0;

                if (y == 7) gametype = 7;
                if (y == 8) gametype = 8;
                if (y == 9) gametype = 9;
                if (y == 10) gametype = 10;
                if (y >= 11 && y <= 12) gametype = 11;
                if (y == 13) gametype = 12;
                if (y == 14) gametype = 13;
                if (y == 15) gametype = 14;
                if (y == 16) gametype = 15;
                if (y == 17) gametype = 16;
                if (y == 18) gametype = 17;
                if (y == 19) gametype = 18;
                if (y == 20) gametype = 19;
                if (y == 21) gametype = 20;
                if (y == 22) gametype = 21;
                if (y == 23) gametype = 22;
                if (y == 24) gametype = 23;
                if (y == 25) gametype = 24;
                if (y == 26) gametype = 25;
                if (y == 27) gametype = 26;
                //more soon...


            }
        }
        //gametype = 9;
        if (gametype != 3 && gametype != 9) gt3s = 0;
        if (gametype != 4) gt4s = 0;
        if (gametype == 1 || gametype == 3 || md == 4 || gametype == 9 || gametype == 11) {
            int gv = 0;
            if (gametype == 3 || gametype == 9 || gametype == 11) gv = 180000000;
            if (TimeUtils.nanoTime() - lastDropTime > 7e8 + 8e7 - hm + gv - speed * 1e6 - acceleration * 5e4 - invincible * 3e5 - invincible2 * 3e5 && unpaused == 1 && readytoplay == 1 && (gametype == 1 || gametype == 3 || gametype == 9 || gametype == 11)) {
                if (potion == 0) spawnRainDrop();
                else spawnCoinRainDrop();
            }

            if ((TimeUtils.nanoTime() - lastcoinDropTime > 7e8 - mp - speed * 8e4 * gametype - moneygang * 7e8 && unpaused == 1 && readytoplay == 1) || mp != 0) if (potion == 0) spawnCoinDrop();
        }
        if (gametype == 2){
            if (TimeUtils.nanoTime() - lastDropTime > 1e9 - speed * 8e5 - invincible * 3e5 && unpaused == 1 && readytoplay == 1 && ((eg_t >= 20 && ad == true) || ad == false)) {
                spawnLineDrop();
                if (tiff < 6) spawnCoinLineDrop(205, -30);
                tiff++;
            }
            if (gt2s == 7) gametype = 1;
        }
        if (gametype == 3 || gametype == 9 || gametype == 11) {
            if (readytoplay == 1) spawnSpike();
            if (readytoplay == 1 && unpaused == 1 && acceleration == 0 && dx > -12 && ad == false && md != 1) {
                if (md != 1 && md != 6) {
                    if ((bucket.x < 20 || bucket.x > 430) && invincible == 0) {
                        unpaused = 0;
                        wonred = false;
                    }
                    if ((sucket.x < 20 || sucket.x > 430) && invincible2 == 0) {
                        unpaused = 0;
                        wonred = true;
                    }
                }
            }
        }
        if (gametype == 4) {
            if (gt4s == 0) {
                Random ii = new Random(); int _ii = ii.nextInt(2);
                spawnBodyLineDrop(_ii);
            }
            if (TimeUtils.nanoTime() - lastDropTime > 9e9 + 6e8 && unpaused == 1 && ad == false) {
                gt4s = 0;
                gametype = 1;
            }
        }
        if (gametype == 5 && unpaused == 1) {
            if (gt5s == 30) {
                for (int i = 0; i < 3; i++) {
                    spawnDrop(0, 500 + 810 + i * 500);
                    spawnDrop(410, 500 + 860 + i * 500);
                    spawnDrop(0, 500 + 910 + i * 500);
                    spawnDrop(410, 500 + 960 + i * 500);

                    spawnCoinLineDrop(212, 500 + 0 + i * 500);

                    spawnDrop(0, 500 + 1210 + i * 500);
                    spawnDrop(410, 500 + 1260 + i * 500);
                    spawnDrop(0, 500 + 1310 + i * 500);
                    spawnDrop(410, 500 + 1360 + i * 500);
                }
            }
            gt5s++;
            if (gt5s > 380 + 160 + 70) {
                gt5s = 0;
                gametype = 1;
            }
        }
        if (gametype == 6) {
            if (gt6s == 30) {
                for (int i = 0; i < 10; i++) spawnDrop(30, 810 + i * 130);
                for (int i = 0; i < 10; i++) spawnDrop(390, 810 + i * 130);
                for (int i = 0; i < 10; i++) spawnDrop(70, 880 + i * 130);
                for (int i = 0; i < 10; i++) spawnDrop(350, 880 + i * 130);

                for (int i = 0; i < 10; i++) spawnCoin(233, 810 + i * 130);
            }
            gt6s++;
            if (gt6s > 300) {
                gt6s = 0;
                gametype = 1;
            }
        }
        if (gametype == 7) {
            if (gt7s == 30) {
                for (int m = 0; m < 3000; m += 1800) {
                    for (int i = 0; i < 2; i++) spawnDrop(0 + 62 * i, 810 + m);
                    for (int i = 0; i < 3; i++) spawnDrop(480 - 62 * i, 810 + m);
                    for (int i = 0; i < 45; i++) spawnDrop(62, 840 + i * 30 + m);
                    for (int i = 0; i < 45; i++) spawnDrop(480 - 62 * 2, 840 + i * 30 + m);
                    for (int i = 0; i < 2; i++) spawnDrop(0 + 62 * i, 840 + 45 * 30 + m);
                    for (int i = 0; i < 3; i++) spawnDrop(480 - 62 * i, 840 + 45 * 30 + m);
                }
                spawnItem(2, 80, 840 + 45 * 30 + 180);
                spawnItem(1, 400, 840 + 45 * 30 + 180);

                for (int i = 0; i < 26; i++) spawnCoin(233, 810 + i * 130);
            }
            gt7s++;
            if (gt7s > 310) {
                gt7s = 0;
                gametype = 1;
            }
        }
        if (gametype == 8) {
            if (gt8s == 30) {
                for (int i = 0; i < 200; i += 70) {
                    spawnCoin(20, 810 + i);
                    spawnCoin(50, 830 + i);
                    spawnCoin(70, 850 + i);
                    spawnCoin(90, 870 + i);
                    spawnCoin(110, 850 + i);
                    spawnCoin(130, 830 + i);
                    spawnCoin(150, 810 + i);

                    int tt = 300;
                    spawnCoin(30 + tt, 810 + i);
                    spawnCoin(50 + tt, 830 + i);
                    spawnCoin(70 + tt, 850 + i);
                    spawnCoin(90 + tt, 870 + i);
                    spawnCoin(110 + tt, 850 + i);
                    spawnCoin(130 + tt, 830 + i);
                    spawnCoin(150 + tt, 810 + i);

                    tt = 150;
                    spawnCoin(30 + tt, 810 + i);
                    spawnCoin(50 + tt, 830 + i);
                    spawnCoin(70 + tt, 850 + i);
                    spawnCoin(90 + tt, 870 + i);
                    spawnCoin(110 + tt, 850 + i);
                    spawnCoin(130 + tt, 830 + i);
                    spawnCoin(150 + tt, 810 + i);
                }
            }
            gt8s++;
            if (gt8s > 120) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 10) {
            if (gt7s == 30) {
                for (int i = 0; i < 9; i++) spawnDrop(30, 810 + i * 400);
                for (int i = 0; i < 9; i++) spawnDrop(390, 810 + i * 400);
                for (int i = 0; i < 6; i++) spawnDrop(210, 910 + i * 400);

                for (int i = 0; i < 9; i++) spawnCoin(340, 810 + i * 400);
                for (int i = 0; i < 9; i++) spawnCoin(120, 810 + i * 400);

            }
            gt7s++;
            if (gt7s > 650) {
                gt7s = 0;
                gametype = 1;
            }
        }
        if (gametype == 11) {
            gt7s++;
            if (gt7s == 3 && soundon == 1) danger.play();
            if (gt7s == 1) {
                Random iir = new Random(); ftype = iir.nextInt(3);
            }
            if (ftype == 0) {
                if (gt7s == 2) {
                    utrx = 0;
                }
                if (gt7s < 50) {
                    utrx += 3;
                    utry++;
                }
                if (gt7s < 200 && gt7s > 140) {
                    utrx += 3;
                    utry--;
                }
                if (gt7s > 260 && gt7s < 420 && utrx > 0) {
                    utrx -= 5;
                    utry--;
                }
            }

            if (ftype == 1) {
                if (gt7s == 2) {
                    utrx = 500;
                }
                if (gt7s < 50) {
                    utrx -= 4;
                    utry++;
                }
                if (gt7s < 200 && gt7s > 140) {
                    utrx -= 4;
                    utry--;
                }
                if (gt7s > 260 && gt7s < 420 && utrx < 800) {
                    utrx += 4;
                    utry--;
                }
            }

            if (ftype == 2) {
                if (gt7s == 2) {
                    utrx = 220;
                    utry = 150;
                }
                if (gt7s < 50) {
                    utry -= 3;
                }
                if (gt7s < 180 && gt7s > 140) {
                    utrx -= 4;
                    utry -= 3;
                }
                if (gt7s > 260 && gt7s < 420 && utrx < 800) {
                    utrx += 4;
                    utry -= 3;
                }
            }


            if (gt7s % 30 == 0 && gt7s > 50) {
                Rectangle bulletdrop = new Rectangle();
                bulletdrop.x = utrx - 26;
                bulletdrop.y = 805;
                bulletdrop.width = 2;
                bulletdrop.height = 10;
                bulletdrops.add(bulletdrop);
                if (soundon == 1) blast.play(0.2f);
            }


            if (gt7s > 420) {
                gt7s = 0;
                utrx = utry = -20;
                gametype = 1;
            }
        }
        if (gametype == 1 && tf != 0) {
            tf++;
            if (tf > 150) tf = 0;
        }


        if (gametype == 12) {
            if (gt8s == 10) {
                for (int i = 0; i < 1000; i += 140) {
                    int tt = 150;
                    spawnCoin(30 + tt, 810 + i);
                    spawnCoin(50 + tt, 830 + i);
                    spawnCoin(70 + tt, 850 + i);
                    spawnCoin(90 + tt, 870 + i);
                    spawnCoin(110 + tt, 850 + i);
                    spawnCoin(130 + tt, 830 + i);
                    spawnCoin(150 + tt, 810 + i);
                }
            }
            gt8s++;
            if (gt8s > 180 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 13) {
            if (gt8s == 30) {
                for (int i = 0; i < 200; i += 70) {
                    int tt = 150;
                    spawnCoin(30 + tt, 810 + i);
                    spawnCoin(50 + tt, 830 + i);
                    spawnCoin(70 + tt, 850 + i);
                    spawnCoin(90 + tt, 870 + i);
                    spawnCoin(110 + tt, 850 + i);
                    spawnCoin(130 + tt, 830 + i);
                    spawnCoin(150 + tt, 810 + i);
                }


                for (int i = 0; i < 200; i += 70) {
                    int tt = 150;
                    spawnCoin(30 + tt, 810 + i + 300);
                    spawnCoin(50 + tt, 830 + i + 300);
                    spawnCoin(70 + tt, 850 + i + 300);
                    spawnCoin(90 + tt, 870 + i + 300);
                    spawnCoin(110 + tt, 850 + i + 300);
                    spawnCoin(130 + tt, 830 + i + 300);
                    spawnCoin(150 + tt, 810 + i + 300);
                }

                for (int i = 0; i < 200; i += 70) {
                    int tt = 150;
                    spawnCoin(30 + tt, 810 + i + 600);
                    spawnCoin(50 + tt, 830 + i + 600);
                    spawnCoin(70 + tt, 850 + i + 600);
                    spawnCoin(90 + tt, 870 + i + 600);
                    spawnCoin(110 + tt, 850 + i + 600);
                    spawnCoin(130 + tt, 830 + i + 600);
                    spawnCoin(150 + tt, 810 + i + 600);
                }

            }
            gt8s++;
            if (gt8s > 260 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 14) {
            if (gt8s == 10) {
                for (int i = 0; i < 1300; i += 250) {
                    int tt = 90;
                    spawnCoin(30 + tt, 810 + i);
                    spawnCoin(50 + tt, 810 + i);
                    spawnCoin(70 + tt, 810 + i);

                    spawnCoin(30 + tt, 830 + i);
                    spawnCoin(70 + tt, 830 + i);

                    spawnCoin(30 + tt, 850 + i);
                    spawnCoin(50 + tt, 850 + i);
                    spawnCoin(70 + tt, 850 + i);


                    tt = 270;
                    spawnCoin(30 + tt, 810 + i + 100);
                    spawnCoin(50 + tt, 810 + i + 100);
                    spawnCoin(70 + tt, 810 + i + 100);

                    spawnCoin(30 + tt, 830 + i + 100);
                    spawnCoin(70 + tt, 830 + i + 100);

                    spawnCoin(30 + tt, 850 + i + 100);
                    spawnCoin(50 + tt, 850 + i + 100);
                    spawnCoin(70 + tt, 850 + i + 100);
                }
            }
            gt8s++;
            if (gt8s > 180 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 15) {
            if (gt8s == 70) {
                for (int i = 0; i < 250; i += 25) spawnCoin(240, 810 + i);
                int tt = 150, pp = 220;
                spawnCoin(30 + tt, 810 + pp);
                spawnCoin(50 + tt, 830 + pp);
                spawnCoin(70 + tt, 850 + pp);
                spawnCoin(90 + tt, 870 + pp);
                spawnCoin(110 + tt, 850 + pp);
                spawnCoin(130 + tt, 830 + pp);
                spawnCoin(150 + tt, 810 + pp);
            }
            gt8s++;
            if (gt8s > 180 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 16) {
            int tt = 30;
            if (gt8s == 70) {
                for (int i = 0; i < 950; i += 180) {
                    tt += 50;
                    spawnCoin(tt, 810 + i);
                    spawnCoin(tt, 830 + i);
                    spawnCoin(tt, 850 + i);
                    spawnCoin(tt, 870 + i);
                    spawnCoin(tt, 890 + i);
                    spawnCoin(tt, 910 + i);
                    spawnCoin(tt, 930 + i);
                }
            }
            gt8s++;
            if (gt8s > 180 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 17) {
            int tt = 30;
            if (gt8s == 70) {
                for (int i = 0; i < 900; i += 300) {
                    spawnCoin(tt, 810 + i);
                    //spawnCoin(tt, 830 + i);
                    spawnCoin(tt, 850 + i);
                    //spawnCoin(tt, 870 + i);
                    spawnCoin(tt, 890 + i);
                    //spawnCoin(tt, 910 + i);
                    spawnCoin(tt, 930 + i);

                    spawnCoin(tt + 20, 810 + i);
                    //spawnCoin(tt + 20, 830 + i);
                    spawnCoin(tt + 20, 850 + i);
                    //spawnCoin(tt + 20, 870 + i);
                    spawnCoin(tt + 20, 890 + i);
                    //spawnCoin(tt + 20, 910 + i);
                    spawnCoin(tt + 20, 930 + i);

                    spawnCoin(tt + 40, 810 + i);
                    //spawnCoin(tt + 40, 830 + i);
                    spawnCoin(tt + 40, 850 + i);
                    //spawnCoin(tt + 40, 870 + i);
                    spawnCoin(tt + 40, 890 + i);
                    //spawnCoin(tt + 40, 910 + i);
                    spawnCoin(tt + 40, 930 + i);

                    spawnCoin(tt + 60, 810 + i);
                    //spawnCoin(tt + 60, 830 + i);
                    spawnCoin(tt + 60, 850 + i);
                    //spawnCoin(tt + 60, 870 + i);
                    spawnCoin(tt + 60, 890 + i);
                    //spawnCoin(tt + 60, 910 + i);
                    spawnCoin(tt + 60, 930 + i);


                    tt += 150;
                }
            }
            gt8s++;
            if (gt8s > 210 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 18) {
            int tt = 240, ttx = 240;
            if (gt8s == 70) {
                for (int i = 0; i < 400; i += 50) {
                    spawnCoin(tt, 810 + i);
                    spawnCoin(ttx, 810 + i);
                    tt -= 20; ttx += 20;
                }
                tt += 20; ttx -= 20;
                for (int i = 0; i < 400; i += 50) {
                    spawnCoin(tt, 1200 + i);
                    spawnCoin(ttx, 1200 + i);
                    tt += 20; ttx -= 20;
                }
            }
            gt8s++;
            if (gt8s > 260 + 160 + 70) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 19) {
            int tt = 470;
            if (gt8s == 70) {
                for (int i = 0; i < 2300; i += 100) {
                    spawnCoin(tt, 810 + i);
                    tt -= 20;
                }
                tt = 10;
                for (int i = 0; i < 2300; i += 100) {
                    spawnCoin(tt, 810 + i);
                    tt += 20;
                }
            }
            gt8s++;
            if (gt8s > 400) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 20) {
            int tt = 450;
            if (gt8s == 70) {
                for (int i = 0; i < 2300; i += 50) {
                    spawnCoin(tt, 810 + i);
                    tt -= 20;
                }
                tt = 10;
                for (int i = 0; i < 2300; i += 50) {
                    spawnCoin(tt, 1600 + i);
                    tt += 20;
                }
                tt = 450;
                for (int i = 0; i < 2300; i += 50) {
                    spawnCoin(tt, 2600 + i);
                    tt -= 20;
                }
            }
            gt8s++;
            if (gt8s > 700) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 21) {
            gt8s++;
            if (gt8s > 70) {
                gt8s = 0;
                gametype = 1;
            }
        }

        if (gametype == 22) {
            if (gt8s == 70) {
                int tt = 145;
                spawnCoin(30 + tt, 840);
                spawnCoin(50 + tt, 835);
                spawnCoin(70 + tt, 832);
                spawnCoin(90 + tt, 829);
                spawnCoin(110 + tt, 832);
                spawnCoin(130 + tt, 835);
                spawnCoin(150 + tt, 840);

                //spawnCoin(10 + tt, 865);
                //spawnCoin(170 + tt, 865);
                spawnCoin(30 + tt, 860);
                spawnCoin(50 + tt, 860);
                spawnCoin(70 + tt, 860);
                spawnCoin(90 + tt, 860);
                spawnCoin(110 + tt, 860);
                spawnCoin(130 + tt, 860);
                spawnCoin(150 + tt, 860);

                for (int i = 0; i < 70; i += 20) {
                    spawnCoin(240 - 20 - 15, 810 + i + 100);
                    spawnCoin(240 + 20, 810 + i + 100);
                }
            }
            gt8s++;
            if (gt8s > 170) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 23) {
            if (gt8s == 70) {
                int tt = 10;
                for (int i = 0; i < 1200; i += 20) {
                    spawnCoin(30 + tt, 810 + i);
                    if (i > 200 && i < 400 || i > 800 && i < 1000) tt += 20;
                }
            }
            gt8s++;
            if (gt8s > 500) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 24) {
            if (gt8s == 70) {
                for (int i = 0; i < 800; i += 22) {
                    int tt = 30;
                    if (i > 200 && i < 400 || i > 600 && i < 800) tt = 405;
                    spawnCoin(tt, 810 + i);
                }
            }
            gt8s++;
            if (gt8s > 300) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 25) {
            if (gt8s == 70) {
                for (int i = 0; i < 350; i += 22) {
                    int tt = 30;
                    spawnCoin(tt, 810 + i);
                    spawnCoin(tt + 20, 810 + i);
                    spawnCoin(tt + 40, 810 + i);
                    spawnCoin(tt + 60, 810 + i);
                }
            }
            gt8s++;
            if (gt8s > 300) {
                gt8s = 0;
                gametype = 1;
            }
        }
        if (gametype == 26) {
            if (gt8s == 70) {
                for (int i = 0; i < 550; i += 21) {
                    int tt = 360;
                    if (i % 2 == 0) {
                        spawnCoin(tt, 810 + i);
                        //spawnCoin(tt + 20, 810 + i);
                        spawnCoin(tt + 40, 810 + i);
                        //spawnCoin(tt + 60, 810 + i);
                    } else {
                        //spawnCoin(tt, 810 + i);
                        spawnCoin(tt + 20, 810 + i);
                        //spawnCoin(tt + 40, 810 + i);
                        spawnCoin(tt + 60, 810 + i);
                    }
                }
            }
            gt8s++;
            if (gt8s > 300) {
                gt8s = 0;
                gametype = 1;
            }
        }

    }

    private void spawnItem(int type) {
        Rectangle item = new Rectangle();
        Random r = new Random();
        item.x = r.nextInt(480 - 64);
        item.y = 810;
        item.width = 28;
        item.height = 28;
        //type = 2;
        if (md != 4) {
            if (type == 0) magnetdrops.add(item);
            if (type == 1) accdrops.add(item);
            if (type == 2) bubdrops.add(item);
            if (type == 3 && gametype == 1 && tf == 0) potiondrops.add(item);
        } else {
            if (type == 1) accdrops.add(item);
            if (type == 2) bubdrops.add(item);
            if (type == 4 || type == 0 || type == 3) {
                pigdrops.add(item);
                Random tr = new Random();
                int y = tr.nextInt(3);
                if (y == 1) {
                    Rectangle itm = new Rectangle();
                    itm.x = item.x;
                    itm.y = item.y + 60;
                    itm.width = 28;
                    itm.height = 28;
                    magnetdrops.add(itm);
                }
            }
        }

        lastitemDropTime = TimeUtils.nanoTime();
    }


    private void spawnItem(int type, int x, int y) {
        Rectangle item = new Rectangle();
        item.x = x;
        item.y = y;
        item.width = 28;
        item.height = 28;

        if (type == 0) magnetdrops.add(item);if (type == 1) accdrops.add(item);if (type == 2) bubdrops.add(item);if (type == 3) potiondrops.add(item);

        lastitemDropTime = TimeUtils.nanoTime();
    }

    private void spawnCoin(int x, int y) {
        Rectangle coindrop = new Rectangle();
        coindrop.x = x;
        coindrop.y = y;
        coindrop.width = 15;
        coindrop.height = 15;
        coindrops.add(coindrop);
    }

    private void spawnDrop(int x, int y) {
        Rectangle raindrop = new Rectangle();
        raindrop.x = x;
        raindrop.y = y;
        raindrop.width = PL_WIDTH;
        raindrop.height = PL_HEIGHT;
        raindrops.add(raindrop);
    }

    private void spawnBodyLineDrop(int t) {
        gt4s++;
        int _x;
        if (t == 1) for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = (i * 32);
            else raindrop.x = 640 - (i * 32);

            _x = (int) raindrop.x + 140;
            if (i % 3 == 0) spawnCoin(_x, 810 + i * 32);

            raindrop.y = 810 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = 480 - (i * 32);
            else raindrop.x = -120 + (i * 32);

            _x = (int)raindrop.x - 140;
            if (i % 3 == 0) spawnCoin(_x, 1310 + i * 32);

            raindrop.y = 1310 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = (i * 32);
            else raindrop.x = 640 - (i * 32);

            _x = (int)raindrop.x + 140;
            if (i % 3 == 0) spawnCoin(_x, 1810 + i * 32);

            raindrop.y = 1810 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = 480 - (i * 32);
            else raindrop.x = -120 + (i * 32);

            _x = (int)raindrop.x - 140;
            if (i % 3 == 0) spawnCoin(_x, 2310 + i * 32);

            raindrop.y = 2310 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = (i * 32);
            else raindrop.x = 640 - (i * 32);

            _x = (int)raindrop.x + 140;
            if (i % 3 == 0) spawnCoin(_x, 2810 + i * 32);

            raindrop.y = 2810 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = 480 - (i * 32);
            else raindrop.x = -120 + (i * 32);

            _x = (int)raindrop.x - 140;
            if (i % 3 == 0) spawnCoin(_x, 3310 + i * 32);

            raindrop.y = 3310 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = (i * 32);
            else raindrop.x = 640 - (i * 32);

            _x = (int)raindrop.x + 140;
            if (i % 3 == 0) spawnCoin(_x, 3810 + i * 32);

            raindrop.y = 3810 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        if (t == 0) for (int i = 0; i < 21; i++) {
            if (i % 2 == 0) continue;
            Rectangle raindrop = new Rectangle();
            if (i < 10) raindrop.x = 480 - (i * 32);
            else raindrop.x = -120 + (i * 32);

            _x = (int)raindrop.x - 140;
            if (i % 3 == 0) spawnCoin(_x, 3310 + i * 32);

            raindrop.y = 4310 + i * 30;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        lastDropTime = TimeUtils.nanoTime();
    }


    private void spawnLineDrop() {
        gt2s++;
        Random random = new Random();
        fi = random.nextInt(6);
        for (int i = 0; i < 7; i++) {
            if (i == fi || i == fi + 1 || i == fi + 2) continue;
            Rectangle raindrop = new Rectangle();
            raindrop.x = i * 70;
            raindrop.y = 810;
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
            raindrops.add(raindrop);
        }
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnCoinLineDrop(int t, int c) {
        if (t == -1) {
            Random random = new Random();
            int k = random.nextInt(450 - 64);
            k += 20;
        }
        else k = t;
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
            if (i == j && i == 1) continue;
            Rectangle coindrop = new Rectangle();
            coindrop.x = k + i * 20;
            coindrop.y = c + 1100 - j * 20;
            coindrop.width = 15;
            coindrop.height = 15;
            coindrops.add(coindrop);
        }
        Rectangle coindrop0 = new Rectangle();
        coindrop0.x = k - 20;
        coindrop0.y = c + 1100 - 1 * 20;
        coindrop0.width = 15;
        coindrop0.height = 15;
        coindrops.add(coindrop0);

        Rectangle coindrop1 = new Rectangle();
        coindrop1.x = k + 3 * 20;
        coindrop1.y = c + 1100 - 1 * 20;
        coindrop1.width = 15;
        coindrop1.height = 15;
        coindrops.add(coindrop1);

        Rectangle coindrop2 = new Rectangle();
        coindrop2.x = k + 1 * 20;
        coindrop2.y = c + 1100 - 3 * 20;
        coindrop2.width = 15;
        coindrop2.height = 15;
        coindrops.add(coindrop2);


        Rectangle coindrop3 = new Rectangle();
        coindrop3.x = k + 1 * 20;
        coindrop3.y = c + 1100 + 1 * 20;
        coindrop3.width = 15;
        coindrop3.height = 15;
        coindrops.add(coindrop3);

        lastcoinDropTime = TimeUtils.nanoTime();
    }

    private void spawnCoinRainDrop() {
        Random random = new Random(); int k = random.nextInt(480 - 64);

        Rectangle coindrop = new Rectangle();
        coindrop.x = k;
        coindrop.y = 810;
        coindrop.width = 25;
        coindrop.height = 25;
        coindrops.add(coindrop);

        Rectangle coindrop1 = new Rectangle();
        coindrop1.x = k + 18;
        coindrop1.y = 810;
        coindrop1.width = 25;
        coindrop1.height = 25;
        coindrops.add(coindrop1);

        Rectangle coindrop2 = new Rectangle();
        coindrop2.x = k + 18 * 2;
        coindrop2.y = 810;
        coindrop2.width = 25;
        coindrop2.height = 25;
        coindrops.add(coindrop2);

        Rectangle coindrop3 = new Rectangle();
        coindrop3.x = k + 18 * 3;
        coindrop3.y = 810;
        coindrop3.width = 25;
        coindrop3.height = 25;
        coindrops.add(coindrop3);

        lastDropTime = TimeUtils.nanoTime();
    }


    private void spawnRainDrop() {
        Rectangle raindrop = new Rectangle();
        Random random = new Random();
        raindrop.x = random.nextInt(480 - 64);
        raindrop.y = 810;
        if (gametype != 1) {
            raindrop.width = PL_WIDTH;
            raindrop.height = PL_HEIGHT;
        } else {
            raindrop.width = 15 + random.nextInt(4) * 50 + random.nextInt(2);
            raindrop.height = PL_HEIGHT;
            if (raindrop.width == 15) raindrop.height = 150;
            if (raindrop.width == 16) raindrop.height = 90;

            int ind = random.nextInt(4);
            if (ind == 0) raindrop.x = 0;
            if (ind == 1) raindrop.x = 415;
        }
        lx = raindrop.x;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
        lt = gametime;
    }

    private void spawnCoinDrop() {
        Rectangle coindrop = new Rectangle();
        Random r = new Random();
        coindrop.x = r.nextInt(480 - 64);
        coindrop.y = 810;
        coindrop.width = 15;
        coindrop.height = 15;
        if (gametype == 1) coindrop.width = coindrop.height = 25;

        if (gametime - lt > 10) {
            if (Math.abs(lx - coindrop.x) < 40) lx = coindrop.x + 40;
            coindrops.add(coindrop);
            lastcoinDropTime = TimeUtils.nanoTime();
        }
    }

    private void ifLost() {
        if (unpaused == 1) return;
        if (md == 3 || md == 5) {
            attempts--;
            reloadOnAd();
            return;
        }
        if (md == 7) {
            attempts--;
            reloadOnAd();
            return;
        }

        savebMask();
        prefs.putInteger("p_coins", coins);

        if (x < 0) x += 12;
        else x = 0;
        if (l < 0.95) l += 0.05f;
        if (ll < 1 && l >= 0.65) ll += 0.05f;
        game.batch.setColor(1,1,1, 0.3f);
        game.batch.draw(dropDark, -120, -120, 670, 1010);

        game.batch.setColor(1,1,1, ll);
        if (retry == true) game.batch.draw(retryBtn_, 109 - x, 269 - x, 114 + x, 114 + x);
        else game.batch.draw(retryBtn, 105 - x, 265 - x, 118 + x, 118 + x);

        if (reload) game.batch.draw(watchadBtn_, 261 - x, 269 - x, 114 + x, 114 + x);
        else game.batch.draw(watchad, 257 - x, 265 - x, 118 + x, 118 + x);


        game.batch.setColor(1,1,1, l);
        wfont.draw(game.batch, "WINNER", 125, 747 - x);
        game.batch.draw(coinsign, 223, 143 + x / 2, 42, 42);


        if (wonred) game.batch.draw(we1, 180, 523 + x / 2, 120, 120);
        else game.batch.draw(we2, 180, 523 + x / 2, 120, 120);

        long m = -1, p = points;
        while (p > 10) { p /= 10; m++; }

        //points  = 0;
        if (md != 8) font.draw(game.batch, "SCORE: " + points, 190 - 7 * m , 450);
        else font.draw(game.batch, "SCORE: " + points * 59, 190 - 7 * m , 450);

        m = 0; p = coins;
        while (p > 10) { p /= 10; m++; }

        font.setColor(1,0.75f,0, 1);
        font.draw(game.batch, "" + coins, 237 - 6 * m, 125 + x / 2);
        font.setColor(1,1,1, 1);

        game.batch.setColor(1,1,1, 1);


        if (Gdx.input.justTouched()) {
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (br.contains(touch.x, touch.y) && readytoplay == 1) {
                if (!retry) {
                    pl++;
                    prefs.putInteger("p_pl", pl);
                }
                bc = 0;
                retry = true;
            }
            if (ba.contains(touch.x, touch.y) && ll >= 0.95f && md != 8) {
                retry = reload = true;
            }
        }

        if (retry == true) f += 0.1f;
        if (f > 1.f) {
            mb = 0.3f; l = 0.3f; ll = 0.f; x = -40; xx = -20; _xx = 0;
            points = 0;
            bucket.x = sucket.x = 480 / 2 - 64 / 2;
            bucket.y = sucket.y = 300;
            bucket.width = sucket.width = 40;
            bucket.height = sucket.height = 40;
            if (md == 6) bucket.width = bucket.height = 16;
            speed = 0;
            ccn = 0;
            ss = 0; eg = 1; eg_t = 0; dx = -30;
            gt3s = gt2s = gt4s = gt5s = gt6s = gt7s = gt8s = 0;
            magnet = magnet2 = acceleration = invincible = invincible2 = potion = 0;
            del = 0;
            utrx = -20;
            if (reload) {
                sndback.stop();
                game.setScreen(new GameScreen(game));
            }
            if (readytoupdate == true) { f = 0; retry = false; unpaused = 1; ad = false;  }
        }

    }

    private void reloadOnAd() {
        ad = true;
        eg = 1; eg_t = 0;
        unpaused = 1;
        mb = 0.3f; l = 0.3f; ll = 0.f; x = -40; xx = -20; _xx = 0;
        //ss = 0;
        eg = 1; eg_t = 0;
    }

    private void drawBackground(int type) {
        if (type == 1) { //winter
            game.batch.draw(bgw, 0, 0, 480, 800);
        }
        if (type == 0) { //summer
            game.batch.draw(bgs, 0, 0, 480, 800);
            game.batch.draw(mill, 26, 219, 60, 60, 120, 120, 1, 1, tttt, false);
            tttt += 0.1f;
        }
        if (type == 3) { //beach
            int tyy = 479;
            game.batch.draw(bgbb, 0, 0, 480, 800);

            game.batch.draw(btide, 0 - rr / 2 - 95, 0, 480, 800);
            game.batch.draw(btide, 0 - rr / 2 + tyy - 95, 0, 480, 800);

            game.batch.draw(boat, 0 + rr, 170, 95, 95);

            game.batch.draw(ftide, 0 - rr / 2 - 95, 167, 480, 19);
            game.batch.draw(ftide, 0 - rr / 2 + tyy - 95, 167, 480, 19);
            rr += 0.5f;
            if (rr > 490) rr = -90;
            game.batch.draw(bgbf, 0, 0, 480, 800);
        }
        if (type == 4) { //space
            game.batch.draw(space, 0, 0, 480, 800);

            float ryu = 0;
            if (rx < 300) ryu = 0.4f;
            if (rx < 80) {
                ryu = (float)(rx / 10);
                ryu /= 10;
            }
            if (rx > 220 && rx < 400) {
                ryu = (float)((rx - 220) / 10);
                ryu /= 10;
                ryu = 0.5f - ryu;
            }

            game.batch.setColor(1, 1, 1, ryu);
            game.batch.draw(comet, 400 - rx, 800 - rx, 25, 19);
            game.batch.setColor(1, 1, 1, 1);


            rx += 6;
            if (rx > 4000) rx = 0;
        }
        if (type == 7) { //night city
            if (rx % 2 == 0) game.batch.draw(bgn1, 0, 0, 480, 800);
            else game.batch.draw(bgn, 0, 0, 480, 800);
            rx++;
            if (rx > 1000) rx = 0;
        }
        if (type == 5) { //wild west
            game.batch.draw(bgwest, 0, 0, 480, 800);
            game.batch.draw(mill_w, 366, 253, 23, 23, 46, 46, 1, 1, ry, false);
            ry += 3;
            if (ry > 4000000) ry = 0;
        }
        if (type == 6) { //amusement park
            game.batch.draw(amparkb, 0, 0, 480, 800);
            game.batch.draw(amparkaa, 343, 195, 100, 100, 200, 200, 1, 1, rio, false);
            game.batch.draw(amparkf, 0, 0, 480, 800);


            rio++;
            if (rio > 100000) rio = 0;
        }
        if (type == 8) { //asia
            game.batch.draw(asiab, 0, 0, 480, 800);
            game.batch.draw(ballon, -100 + rpox, 500 + rpoy, 60, 57);
            game.batch.draw(asiaf, 0, 0, 480, 800);

            rpox += 0.4f;
            if (rpox > 120 * 8) rpox = 0;

            rpoy += 0.05f;
            if (rpoy > 120) rpoy = 0;
        }
        if (type == 2) { //volcano
            game.batch.draw(bgj, 0, 0, 480, 800);
        }

        if (type == 1 || type == 4) {
            game.batch.setColor(1, 1, 1, 1 - ii);
            game.batch.draw(star, 60, 700, 2, 2);

            game.batch.setColor(1, 1, 1, ii);
            game.batch.draw(star, 200, 760, 2, 2);

            game.batch.setColor(1, 1, 1, ii + 0.2f);
            game.batch.draw(star, 30, 670, 2, 2);

            game.batch.setColor(1, 1, 1, 0.7f - ii);
            game.batch.draw(star, 230, 690, 3, 3);


            game.batch.setColor(1, 1, 1, 1 - ii);
            game.batch.draw(star, 120, 700, 3, 3);

            game.batch.setColor(1, 1, 1, ii);
            game.batch.draw(star, 250, 560, 2, 2);

            game.batch.setColor(1, 1, 1, ii + 0.2f);
            game.batch.draw(star, 30, 570, 2, 2);

            game.batch.setColor(1, 1, 1, 0.7f - ii);
            game.batch.draw(star, 130, 590, 2, 2);


            ii += (0.01f * uy);

            if (ii >= 1) uy = -1;
            if (ii <= 0) uy = 1;
        }

    }

    private void niU() {
        if (showup == false || rdr == -1) return;
        font.setColor(1,1,1, nnn);
        game.batch.setColor(1,1,1, nnn);

        int rtt = 50;
        if (rdr == 9 && soundon == 1) congrats.play();
        if (rdr < 60) {
            game.batch.draw(tick, 115 - rtt, 790 - rdr, 24, 24);
            font.draw(game.batch, "NEW LOCATION UNLOCKED", 150 - rtt, 810 - rdr);
        } else {
            game.batch.draw(tick, 115 - rtt, 790 - 63, 24, 24);
            font.draw(game.batch, "NEW LOCATION UNLOCKED", 150 - rtt, 810 - 63);
        }

        font.setColor(1,1,1, 1);
        game.batch.setColor(1,1,1, 1);

        if (rdr < 60) nnn += 0.05f;
        if (nnn > 0 && rdr >= 120) nnn -= 0.06f;
        if (nnn <= 0 && rdr >= 120) {
            showup = false;
            rdr = -1;
        }
        if (rdr < 120) rdr += 3;
    }

    public MultiGameScreen (final Drop game) {
        this.game = game;

        pl = prefs.getInteger("p_pl");
        soundon = prefs.getInteger("p_snd");
        coins = prefs.getInteger("p_coins");
        highscore = prefs.getInteger("p_hs");
        md = 10;
        bmask = prefs.getString("p_b");
        loadbMask();
        bask = prefs.getString("p_bm");
        bg = prefs.getInteger("p_bg");

        for (int i = 0; i < 9; i++) if (bask.charAt(i) == '1') it5++;

        sndback = Gdx.audio.newMusic(Gdx.files.internal("snd/snd.mp3"));
        sndback.setLooping(true);
        snd = false;
        if (soundon == 1) {
            sndback.play();
            snd = true;
        }
        //bg = 4;

        we1 = new Texture("we1.png");
        we2 = new Texture("we2.png");

        //vdropImage = new Texture("plat/_" + (bg + 1) + ".png");
        vdropImage = new Texture("plat/_1.png");

        //vvdropImage = new Texture("plat/__" + (bg + 1) + ".png");
        vvdropImage = new Texture("plat/__1.png");

        dropImage = new Texture("plat/" + (bg + 1) + ".png");
        dropImaget = new TextureRegion(dropImage);

        dropDark = new Texture("red.png");

        blast = Gdx.audio.newSound(Gdx.files.internal("snd/blast.mp3"));
        snddrop = Gdx.audio.newSound(Gdx.files.internal("snd/coin.mp3"));
        danger = Gdx.audio.newSound(Gdx.files.internal("snd/danger.mp3"));
        mg = Gdx.audio.newSound(Gdx.files.internal("snd/magnet.mp3"));
        acd = Gdx.audio.newSound(Gdx.files.internal("snd/acc.mp3"));
        bbl = Gdx.audio.newSound(Gdx.files.internal("snd/bubble.mp3"));
        congrats = Gdx.audio.newSound(Gdx.files.internal("snd/congrats.mp3"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new FitViewport(480, 800, camera);


        circleImage = new Texture("test.png");
        circleImage2 = new Texture("testop.png");

        watchadBtn = new Texture("home.png");
        retryBtn = new Texture("retrynew.png");
        retryBtn_ = new Texture("retrynew_.png");
        gameover = new Texture("go.png");
        coinsign = new Texture("coin.png");
        watchad = new Texture("home.png");
        watchadBtn_ = new Texture("home1.png");
        sndon = new Texture("sndon.png");
        sndoff = new Texture("sndoff.png");
        maps = new Texture("maps.png");
        cir = new Texture("cir.png");
        mpl = new Texture("mpl.png");
        bubble = new Texture("bubble.png");

        spike = new Texture("spike.png");
        if (bg == 4 || bg == 5 || bg == 7) spike = new Texture("spikew.png");


        magsign = new Texture("manet.png");
        energy = new Texture("engy.png");
        potionicon = new Texture("potion.png");
        warn = new Texture("warn.png");


        bgw = new Texture("back/winter.png");
        star = new Texture("back/star.png");
        space = new Texture("back/space.png");
        comet = new Texture("back/comet.png");

        bgs = new Texture("back/summer.png");
        bgs_m = new Texture("back/mill.png");
        mill = new TextureRegion(bgs_m);


        bgbb = new Texture("back/bbeach.png");
        bgbf = new Texture("back/fbeach.png");
        boat = new Texture("back/boat.png");
        btide = new Texture("back/bigtide.png");
        ftide = new Texture("back/tide.png");


        bgj = new Texture("back/volcano.png");
        bgn = new Texture("back/night.png");
        bgn1 = new Texture("back/night_1.png");


        bgwest = new Texture("back/west.png");
        millww = new Texture("back/mill_w.png");
        mill_w = new TextureRegion(millww);


        amparkb = new Texture("back/amparkb.png");
        amparkf = new Texture("back/amparkf.png");
        amparka = new Texture("back/boat_a.png");
        amparkaa = new TextureRegion(amparka);

        asiab = new Texture("back/asiab.png");
        asiaf = new Texture("back/asiaf.png");
        ballon = new Texture("back/ballon.png");


        mode[0] = new Texture("items/1.png");
        mode[1] = new Texture("items/_2.png");
        mode[2] = new Texture("items/3.png");
        mode[3] = new Texture("items/4.png");
        mode[4] = new Texture("items/5.png");
        mode[5] = new Texture("items/6.png");
        mode[6] = new Texture("items/7.png");
        mode[7] = new Texture("items/8.png");
        mode[8] = new Texture("items/9.png");


        btip = new Texture("btip.png");
        rtip = new Texture("rtip.png");


        tick = new Texture("_tick.png");

        wt = new Texture("wt.jpg");
        green = new Texture("green.jpg");

        laser = new Texture("laser.png");

        if (md == 3) attempts = 1;
        if (md == 7) attempts = 2;

        io = new TextureRegion(mode[5]);

        bucket = new Rectangle();
        bucket.x = 480 / 2 - 64 / 2;
        bucket.y = 300;
        bucket.width = bucket.height = 40;

        sucket = new Rectangle();
        sucket.x = 480 / 2 - 64 / 2;
        sucket.y = 300;
        sucket.width = sucket.height = 40;

        if (md == 6)  bucket.width = bucket.height = 16;

        //font.getData().setScale(1.1f, 1.1f);
        if (md == 8) hm = 125000000;

        magnetdrops = new Array<Rectangle>();
        raindrops = new Array<Rectangle>();
        coindrops = new Array<Rectangle>();
        accdrops = new Array<Rectangle>();
        bubdrops = new Array<Rectangle>();
        potiondrops = new Array<Rectangle>();
        pigdrops = new Array<Rectangle>();
        bulletdrops = new Array<Rectangle>();

        br = new Rectangle(105, 250, 120, 120);
        ba = new Rectangle(257, 250, 120, 120);

        touch = new Vector3();

    }

    private void loadbMask() {
        for (int i = 0; i < 9; i++) if (bmask.charAt(i) == '1') bb[i] = true;
    }

    private void savebMask() {
        bmask = "";
        for (int i = 0; i < 9; i++) if (bb[i] == true) bmask += "1";
        else bmask += "0";
        prefs.putString("p_b", bmask);
    }

    @Override
    public void render (float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        drawBackground(bg);
        //font.draw(game.batch, "Drops collected: " + dropsGathered, 0, 480);

        if (ad == true) {
            plm += 0.035f * eg;
            eg_t++;
            if (eg_t >= 200) {
                ad = false;
                plm = 1.f;
            }
            if (plm >= 0.7f && eg_t <= 120) eg = -1;
            if (plm <= 0.f) eg = 1;
        }

        if (invincible == 1 || invincible2 == 1) {
            inv_t++;
            if (inv_t >= 370) {
                if (ilm >= 0.7f) ini = -1.f;
                if (ilm <= 0.f) ini = 1.f;
                ilm += 0.04f * ini;
            }
            else { ilm = 1.f; }
            if (inv_t >= 450 && ilm <= 0.3f) invincible = invincible2 = 0;
        }
        else {
            inv_t = 0;
            ilm = 1.f;
        }

        if (!ad) {
            plm = 1;
            if (sucket.x > bucket.x && sucket.x < bucket.x + bucket.width) plm = 0.5f;
            if (bucket.x > sucket.x && bucket.x < sucket.x + sucket.width) plm = 0.5f;
        }
        game.batch.setColor(1,1,1, plm);
        if (Gdx.input.isTouched() && readytoplay == 1) {
            if (md != 5) {
                if (allowTouch) {
                    allowTouch = false;
                    if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
                        if (bucket.x < 6 || bucket.x > 435) {
                            if (bucket.x < 6) {
                                bucket.x = 12;
                                rightMoving1 = 1;
                            }
                            if (bucket.x > 435) {
                                bucket.x = 422;
                                rightMoving1 = -1;
                            }
                        } else rightMoving1 *= -1;
                    } else {
                        if (sucket.x < 6 || sucket.x > 435) {
                            if (sucket.x < 6) {
                                sucket.x = 12;
                                rightMoving2 = 1;
                            }
                            if (sucket.x > 435) {
                                sucket.x = 422;
                                rightMoving2 = -1;
                            }
                        } else rightMoving2 *= -1;
                    }

                    lastTouchTime = TimeUtils.nanoTime();
                }
            } else if (allowTouchd && Gdx.input.getX() > 5 && Gdx.input.getX() < 430) {
                game.batch.draw(mode[5], bucket.x, bucket.y, bucket.width, bucket.height);
                bucket.x = Gdx.input.getX() + 14;
                allowTouchd = false;
                lastTouchTime = TimeUtils.nanoTime();
            }
        }
        for (Rectangle rt : bulletdrops) {
            game.batch.draw(laser, rt.x - 3, rt.y, 7, 12);
            game.batch.draw(laser, rt.x - 12, rt.y + 4, 7, 12);
            game.batch.draw(laser, rt.x + 6, rt.y + 4, 7, 12);
            //game.batch.draw(bubble, magnet.x + 1, magnet.y - 7, 27, 25);
        }

        game.batch.draw(circleImage2, sucket.x, sucket.y, sucket.width, sucket.height);
        game.batch.draw(circleImage, bucket.x, bucket.y, bucket.width, bucket.height);

        game.batch.setColor(1,1,1, ilm);
        if (invincible == 1) game.batch.draw(bubble, bucket.x - 15, bucket.y - 15, 70, 70);
        if (invincible2 == 1) game.batch.draw(bubble, sucket.x - 15, sucket.y - 15, 70, 70);
        game.batch.setColor(1,1,1, 1);
        if (md == 2 && unpaused == 1) {
            if (Gdx.input.isTouched()) {
                inb = 0;
                if (cnt > 0) {
                    game.batch.draw(mode[md], bucket.x - 15, bucket.y - 15, 70, 70);
                    inb = 1;
                    cnt--;
                    if (cnt % 12 == 0 && soundon == 1) bbl.play(0.3f);
                }
            } else {
                inb = 0;
                if (cnt < 100) cnt++;
            }
            if (unpaused == 1) game.batch.draw(green, 327, 768, 3, cnt / 4);
        }

        for (Rectangle raindrop : raindrops) {
            //game.batch.setColor(1,1,1, 0.2f);
            //if (gametype == 1) game.batch.draw(dropImage, raindrop.x + 5, raindrop.y - 5, raindrop.width - 15, raindrop.height / 2);
            //game.batch.setColor(1,1,1, 1);
            if ((gametype == 9 || gametype == 10 || tf != 0) && gt7s > 100) {

                game.batch.draw(dropImaget, raindrop.x, raindrop.y, PL_WIDTH / 2, PL_HEIGHT / 2, PL_WIDTH, PL_HEIGHT, 1, 1, r);
                game.batch.draw(dropImaget, raindrop.x, raindrop.y, PL_WIDTH / 2, PL_HEIGHT / 2, PL_WIDTH, PL_HEIGHT, 1, 1, r + 90);
                if (unpaused == 0) {
                    game.batch.draw(dropImaget, raindrop.x, raindrop.y, PL_WIDTH / 2, PL_HEIGHT / 2, PL_WIDTH, PL_HEIGHT, 1, 1, r + 45);
                    game.batch.draw(dropImaget, raindrop.x, raindrop.y, PL_WIDTH / 2, PL_HEIGHT / 2, PL_WIDTH, PL_HEIGHT, 1, 1, r + 135);
                }
            }
            else {
                if (raindrop.width > 16) game.batch.draw(dropImage, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
                else if (raindrop.width == 15) game.batch.draw(vdropImage, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
                else game.batch.draw(vvdropImage, raindrop.x, raindrop.y, raindrop.width, raindrop.height);
            }
            if (unpaused == 1) r++;
        }
        for (Rectangle coindrop : coindrops) {
            game.batch.draw(coinsign, coindrop.x + 5, coindrop.y - 9, coindrop.width, coindrop.height);
        }
        getGameType();
        if (unpaused == 1)  {
            font.setColor(1,1,1, 1);
            if (md != 8) font.draw(game.batch, "" + points, 8, 790);
            else font.draw(game.batch, "" + points * 59, 8, 790);
            if (points < ss / 2 + speed / 3 + del / 12) points = ss / 2 + speed / 3 + del / 12;

            game.batch.draw(coinsign, 450, 772, 20, 20);
            font.setColor(1,0.75f,0, 1);

            k = -1; c = coins;
            while (c > 0) { k++; c /= 10; }
            if (coins == 0) k = 0;

            font.draw(game.batch, "" + coins, 432 - k * 13, 790);
            font.setColor(1,1,1, 1);
        }
        if ((gametype == 3 || gametype == 9 || gametype == 11) && readytoplay == 1) {
            game.batch.setColor(1,1,1, yu);
            if (gametype != 11) {
                if (gt3s < 20 || (gt3s > 40 && gt3s < 60)) yu += 0.05f;
                else if (yu > 0.f) yu -= 0.04f;
            } else {
                if (gt7s < 20 || (gt7s > 40 && gt7s < 60)) yu += 0.05f;
                else if (yu > 0.f) yu -= 0.04f;
            }
            game.batch.draw(warn, 221, 750, 38, 34);
            game.batch.setColor(1,1,1, 1);
        }

        drawItems();
        drawMode();
        niU();

        //game.batch.draw(circleImage, utrx - 40, 660 + utry, 40, 40);

        ifLost();

        game.batch.setColor(1,1,1, f);
        game.batch.draw(dropDark, -120, -120, 670, 1010);
        game.batch.setColor(1,1,1, 1);

        if ((md == 3 || md == 5) && ad == true) {
            game.batch.setColor(1,1,1, vvv);
            game.batch.draw(wt, 0, 0, 500, 1010);
            game.batch.setColor(1,1,1, 1);

            vvv += 0.05f;
            if (vvv >= 0.8f) {
                vvv = 0.f;
                md = 10;
                plm = 1;
                ad = false;
                eg_t = 0; eg_t = 101;
            }
        }
        if (md == 7 && ad == true) {
            game.batch.setColor(1,1,1, vvv);
            game.batch.draw(wt, 0, 0, 500, 1010);
            game.batch.setColor(1,1,1, 1);

            vvv += 0.05f;
            if (vvv >= 0.8f) {
                vvv = 0.f;
                md = 3;
                plm = 1;
                ad = false;
                eg_t = 0; eg_t = 101;
            }
        }

        showTip();

        game.batch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //game.setScreen(new MainMenuScreen(game));
            unpaused = 1;
            l = 0.3f; ll = 0.f; x = -40;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { unpaused = 0; }


        if (!(md == 1 && (gametype == 3 || gametype == 9))) {
            if (bucket.x < 0) rightMoving1 = 1;
            if (bucket.x > 480 - 20) rightMoving1 = -1;

            if (sucket.x < 0) rightMoving2 = 1;
            if (sucket.x > 480 - 20) rightMoving2 = -1;
        } else {
            if (bucket.x < 27) rightMoving1 = 1;
            if (bucket.x + bucket.width > 450) rightMoving1 = -1;
        }


        if (md != 5 && (readytoplay == 0 || (bucket.x > 5 && bucket.x < 435))) bucket.x += (400 + speed) * Gdx.graphics.getDeltaTime() * rightMoving1 * unpaused;
        if (md != 5 && (readytoplay == 0 || (sucket.x > 5 && sucket.x < 435))) sucket.x += (400 + speed) * Gdx.graphics.getDeltaTime() * rightMoving2 * unpaused;


        if (bucket.y < 120 && readytoplay == 1) bucket.y += 3;
        if (sucket.y < 120 && readytoplay == 1) sucket.y += 3;


        if (acceleration != 0) del++;

        if (TimeUtils.nanoTime() - lastTouchTime > 1e8 + 5e7) allowTouch = true;
        if (TimeUtils.nanoTime() - lastTouchTime > 1e8 + 5e7) allowTouchd = true;
        if (TimeUtils.nanoTime() - lastSecond > 5e8 && unpaused == 1 && readytoplay == 1) {
            if (speed <= 280) {
                Random iT = new Random(); int ittt = iT.nextInt(2);
                if (ittt == 1) speed++;
            }
            ss++;
            if (acceleration != 0) ss += 6;
            lastSecond = TimeUtils.nanoTime();
        }
        if (TimeUtils.nanoTime() - lastitemDropTime > 18e9 && readytoplay == 1 && unpaused == 1) {
            Random itemType = new Random(); int itType = itemType.nextInt(4);
            if (md == 4) itType++;
            if (tf == 0) spawnItem(itType);
        }


        Iterator<Rectangle> iter = raindrops.iterator();
        Iterator<Rectangle> it = coindrops.iterator();
        Iterator<Rectangle> mit = magnetdrops.iterator();
        Iterator<Rectangle> acc = accdrops.iterator();
        Iterator<Rectangle> bbd = bubdrops.iterator();
        Iterator<Rectangle> ppd = potiondrops.iterator();
        Iterator<Rectangle> ddd = pigdrops.iterator();
        Iterator<Rectangle> ddl = bulletdrops.iterator();


        int jj = 0;
        if (unpaused == 1 || retry == true) {
            while (ppd.hasNext()) {
                Rectangle ey = ppd.next();
                ey.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if ((ey.overlaps(sucket) || ey.overlaps(bucket)) && (eg_t == 0 || eg_t > 100) && unpaused == 1) {
                    ppd.remove();
                    potion = 1;
                    if (soundon == 1) mg.play();
                }
                if (ey.y + 64 < 0) ppd.remove();
            }
            while (bbd.hasNext()) {
                Rectangle ery = bbd.next();
                ery.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if ((ery.overlaps(sucket) || ery.overlaps(bucket)) && (eg_t == 0 || eg_t > 100) && unpaused == 1) {
                    if (ery.overlaps(bucket)) invincible = 1;
                    if (ery.overlaps(sucket)) invincible2 = 1;
                    bbd.remove();
                    inv_t = 0;
                    bc++;
                    if (soundon == 1) bbl.play();
                }
                if (ery.y + 64 < 0) bbd.remove();
            }
            while (acc.hasNext()) {
                Rectangle accr = acc.next();
                accr.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if ((accr.overlaps(sucket) || accr.overlaps(bucket)) && (eg_t == 0 || eg_t > 100) && invincible == 0 && unpaused == 1) {
                    acc.remove();
                    acceleration = 300;
                    if (soundon == 1) acd.play();
                }
                if (accr.y + 64 < 0) acc.remove();
            }
            while (mit.hasNext()) {
                Rectangle manet = mit.next();
                manet.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if ((manet.overlaps(bucket) || manet.overlaps(sucket)) && (eg_t == 0 || eg_t > 100) && invincible == 0 && unpaused == 1) {
                    if (manet.overlaps(bucket)) magnet = 1;
                    if (manet.overlaps(sucket)) magnet2 = 1;
                    mit.remove();
                    if (soundon == 1) mg.play();
                }
                if (manet.y + 64 < 0) mit.remove();
            }
            while (iter.hasNext()) {
                Rectangle raindrop = iter.next();
                raindrop.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;

                if (gametype == 5 && gt5s > 50 + speed / 7) {
                    if (jj % 2 == 0) raindrop.x++;
                    else raindrop.x--;
                }

                if (ad == false && acceleration == 0) {
                    //iter.remove(); /*snddrop.play();*/ dropsGathered++;
                    if (inb == 0) {
                        if (raindrop.overlaps(bucket) && invincible == 0) {
                            unpaused = 0;
                            wonred = false;
                        }
                        if (raindrop.overlaps(sucket) && invincible2 == 0) {
                            unpaused = 0;
                            wonred = true;
                        }
                    }
                }
                if ((raindrop.y + raindrop.height + 64 < 0 && gametype != 5) || retry == true) {
                    iter.remove();
                    if (retry == true) readytoupdate = true;
                }
                jj++;
            }
            while (it.hasNext()) {
                Rectangle coindrop = it.next();
                coindrop.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if (magnet != 0) {
                    coindrop.x += (bucket.x - coindrop.x) * Gdx.graphics.getDeltaTime() * readytoplay * 100;
                    if (coindrop.y + 20 <= bucket.y) coindrop.y += (bucket.y - coindrop.y) * Gdx.graphics.getDeltaTime() * readytoplay * 30;
                }
                if (magnet2 != 0) {
                    coindrop.x += (sucket.x - coindrop.x) * Gdx.graphics.getDeltaTime() * readytoplay * 100;
                    if (coindrop.y + 20 <= bucket.y) coindrop.y += (sucket.y - coindrop.y) * Gdx.graphics.getDeltaTime() * readytoplay * 30;
                }
                if (coindrop.y + 64 < 0) it.remove();
                if (((coindrop.overlaps(sucket) || coindrop.overlaps(bucket)) && ad == false && readytoplay == 1) || retry == true) {
                    it.remove();
                    if (retry == true) readytoupdate = true;
                    coins++;
                    ccn++;
                    if ((gametype == 1 && gt1 > 100) || potion != 0) {
                        coins += 4;
                        ccn += 4;
                    }
                    if (soundon == 1) snddrop.play();
                }
            }
            while (ddd.hasNext()) {
                Rectangle pigbx = ddd.next();
                pigbx.y -= (300 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if (magnet != 0) {
                    pigbx.x += (bucket.x - pigbx.x) * Gdx.graphics.getDeltaTime() * readytoplay * 100;
                    if (pigbx.y < bucket.y) pigbx.y += (bucket.y - pigbx.y) * Gdx.graphics.getDeltaTime() * readytoplay * 10;
                }
                if (pigbx.y + 64 < 0) ddd.remove();
                if (pigbx.overlaps(bucket) && unpaused == 1) {
                    ddd.remove();
                    acd.play();
                    mp = 890000000;
                }
            }
            while (ddl.hasNext()) {
                Rectangle pigb = ddl.next();
                pigb.y -= (620 + speed + acceleration) * Gdx.graphics.getDeltaTime() * readytoplay;
                if (pigb.y + 64 < 0 || retry == true) ddl.remove();
                if (pigb.overlaps(bucket) && unpaused == 1 && invincible == 0 && acceleration == 0 && inb == 0 && readytoplay == 1) {
                    unpaused = 0;
                    wonred = false;
                }
                if (pigb.overlaps(sucket) && unpaused == 1 && invincible2 == 0 && acceleration == 0 && inb == 0 && readytoplay == 1) {
                    unpaused = 0;
                    wonred = true;
                }
            }
        }

        if (potion != 0) potion++;
        if (potion > 75) potion = 0;

        if (mp != 0) mp++;
        if (mp > 890000150) mp = 0;


        if (unpaused == 0 && bb[0] == false && showup == false) {
            rdr = 0;
            bb[0] = true;
            showup = true;
        }
        if (points > 150 && unpaused == 0 && bb[1] == false && showup == false) {
            rdr = 0;
            bb[1] = true;
            showup = true;
        }
        if (ccn >= 1000 && bb[2] == false && showup == false) {
            rdr = 0;
            bb[2] = true;
            showup = true;
        }
        if (TimeUtils.nanoTime() - lastTouchTime > 13e9 && points > 2 && unpaused == 1 && bb[3] == false && showup == false) {
            rdr = 0;
            bb[3] = true;
            showup = true;
        }
        if (bc == 5 && bb[4] == false && showup == false) {
            rdr = 0;
            bb[4] = true;
            showup = true;
        }
        if (it5 >= 5 && bb[5] == false && showup == false) {
            rdr = 0;
            bb[5] = true;
            showup = true;
        }
        if (md == 8 && points * 59 >= 7000 && bb[6] == false && showup == false) {
            rdr = 0;
            bb[6] = true;
            showup = true;
        }
        if (pl == 100 && bb[7] == false && showup == false) {
            rdr = 0;
            bb[7] = true;
            showup = true;
        }


        if (gametype == 1) gt1++;
        else gt1 = 0;

        gametime++;

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
    public void dispose () {
        snddrop.dispose();
        danger.dispose();
        mg.dispose();
        acd.dispose();
        bbl.dispose();
        congrats.dispose();
        blast.dispose();
        dropImage.dispose();
        circleImage.dispose(); retryBtn.dispose(); watchadBtn.dispose(); gameover.dispose(); coinsign.dispose(); watchad.dispose(); retryBtn_.dispose(); watchadBtn_.dispose(); sndon.dispose(); sndoff.dispose(); maps.dispose(); cir.dispose(); mpl.dispose(); bubble.dispose(); spike.dispose(); magsign.dispose(); energy.dispose(); potionicon.dispose(); laser.dispose();
        bgw.dispose(); bgs.dispose(); bgn.dispose(); bgbb.dispose(); bgbf.dispose(); bgj.dispose(); wt.dispose(); green.dispose(); warn.dispose(); tick.dispose(); bgs_m.dispose(); boat.dispose(); btide.dispose(); ftide.dispose(); star.dispose(); space.dispose(); comet.dispose(); bgn1.dispose(); bgwest.dispose(); millww.dispose(); amparkb.dispose(); amparkf.dispose(); amparka.dispose(); asiab.dispose(); asiaf.dispose(); ballon.dispose(); dropDark.dispose(); vdropImage.dispose(); vvdropImage.dispose();
        sndback.dispose();

        font.dispose();
        score.dispose();
    }

    @Override
    public void show() {
        //sndback.play();
    }
}
