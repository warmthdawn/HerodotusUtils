package youyihj.herodotusutils.block.alchemy;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import youyihj.herodotusutils.alchemy.IHasAlchemyFluid;

/**
 * @author youyihj
 */
public abstract class AbstractHasAlchemyFluidTileEntity extends AbstractPipeTileEntity implements IHasAlchemyFluid {
    private Fluid content;
    private Fluid cachedContent;

    @Override
    public Fluid getContainedFluid() {
        return content;
    }

    @Override
    public boolean handleInput(Fluid input, EnumFacing inputSide) {
        if (content == null && inputSide == allowInputSide()) {
            cachedContent = input;
            return true;
        }
        return false;
    }

    @Override
    public void afterModuleMainWork() {
        if (cachedContent != null && content == null) {
            content = cachedContent;
            cachedContent = null;
        }
    }

    @Override
    public void emptyFluid() {
        content = null;
    }

    protected abstract EnumFacing allowInputSide();
}
