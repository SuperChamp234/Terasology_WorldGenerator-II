import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class TutorialWorldRasterizer implements WorldRasterizer {

    private Block dirt;
    private Block grass;
    private Block stone;
    private Block snow;
    private Block sand;

    @Override
    public void initialize() {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("Core:Dirt");
        grass = CoreRegistry.get(BlockManager.class).getBlock("Core:Grass");
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:stone");
        snow = CoreRegistry.get(BlockManager.class).getBlock("Core:snow");
        sand = CoreRegistry.get(BlockManager.class).getBlock("Core:sand");
    }

    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);

            if (position.y < surfaceHeight - 5) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
            }
            else if (position.y < surfaceHeight - 1) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
            }
            else if (position.y < surfaceHeight && surfaceHeight < 80) {
                if (position.y > -3 && position.y < 3) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), sand);
                } else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), grass);
                }
            } else if (position.y < surfaceHeight && surfaceHeight >= 50) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), snow);
            }
        }
    }
}