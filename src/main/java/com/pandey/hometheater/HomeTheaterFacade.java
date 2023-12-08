package com.pandey.hometheater;

public class HomeTheaterFacade {
    Amplifier amp;
    CdPlayer cdPlayer;
    PopcornPopper popper;
    Projector projector;
    Screen screen;
    StreamingPlayer player;
    TheaterLights lights;
    Tuner tuner;

    public HomeTheaterFacade(Amplifier amplifier, CdPlayer cdPlayer, PopcornPopper popcornPopper, Projector projector, Screen screen, StreamingPlayer streamingPlayer, TheaterLights theaterLights, Tuner tuner) {
        this.amp = amplifier;
        this.cdPlayer = cdPlayer;
        this.popper = popcornPopper;
        this.projector = projector;
        this.screen = screen;
        this.player = streamingPlayer;
        this.lights = theaterLights;
        this.tuner = tuner;
    }
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setStreamingPlayer(player);
        amp.setSurroundSound();
        amp.setVolume(5);
        player.on();
        player.play(movie);
    }
    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        player.stop();
        player.off();
    }
    public void listenToRadio(double frequency) {
        System.out.println("Tuning in the airwaves...");
        tuner.on();
        tuner.setFrequency(frequency);
        amp.on();
        amp.setVolume(5);
        amp.setTuner(tuner);
    }
    public void endRadio() {
        System.out.println("Shutting down the tuner...");
        tuner.off();
        amp.off();
    }
}
