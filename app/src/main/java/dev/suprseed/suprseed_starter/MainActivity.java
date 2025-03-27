package dev.suprseed.suprseed_starter;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.DefaultEngineConfiguration;
import dev.suprseed.Engine.EngineActivity;

public class MainActivity extends EngineActivity {
    @Override
    protected BaseEngineConfigurator loadEngineConfig() {
        return new DefaultEngineConfiguration(this.getApplicationContext(), this);
    }

    @Override
    public void initStartingState(RootScene rootScene) {
        rootScene.registerScene(new MainScene("main_scene"));
    }
}
