import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.*;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "tutorialWorld", displayName = "Tutorial World")
public class TutorialWorldGenerator extends BaseFacetedWorldGenerator {
    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public TutorialWorldGenerator(SimpleUri uri){
        super(uri);
    }

    @Override
    protected   WorldBuilder createWorld(){
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(0)
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(0))
                .addProvider(new MountainsProvider())
                .addRasterizer(new TutorialWorldRasterizer())
                .addPlugins();
    }

}