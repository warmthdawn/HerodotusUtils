package youyihj.herodotusutils.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import youyihj.herodotusutils.HerodotusUtils;
import youyihj.herodotusutils.fluid.FluidMana;
import youyihj.herodotusutils.modsupport.modularmachinery.block.BlockMMController;

import java.util.List;

/**
 * @author youyihj
 */
@Mod.EventBusSubscriber
public class BlockRegistry {
    private static final Block FLUID_MANA_BLOCK = new BlockFluidClassic(FluidMana.INSTANCE, Material.WATER).setRegistryName("fluid_mana");
    public static final BlockOreBase RED_ORE = new BlockOreBase("red", 0xfc0d20);
    public static final BlockOreBase YELLOW_ORE = new BlockOreBase("yellow", 0xffd701);
    public static final BlockOreBase BLUE_ORE = new BlockOreBase("blue", 0x00a2dd);
    public static final BlockOreBase RHOMBUS_ORE = new BlockOreBase("rhombus", 0xffffff)
            .setDropItemSupplier(() -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("contenttweaker", "rhombus"))));
    public static final BlockOreBase SPHERICAL_ORE = new BlockOreBase("spherical", 0xffffff)
            .setDropItemSupplier(() -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("contenttweaker", "spherical"))));
    public static final BlockOreBase SQUARE_ORE = new BlockOreBase("square", 0xffffff)
            .setDropItemSupplier(() -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("contenttweaker", "square"))));
    public static final List<BlockOreBase> ORES = Lists.newArrayList(RED_ORE, YELLOW_ORE, BLUE_ORE, RHOMBUS_ORE, SPHERICAL_ORE, SQUARE_ORE);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(FLUID_MANA_BLOCK);
        registry.register(BlockMercury.INSTANCE);
        registry.register(BlockManaLiquidizer.INSTANCE);
        registry.register(BlockCalculatorStructure.STRUCTURE_BLOCK_1);
        registry.register(BlockCalculatorStructure.STRUCTURE_BLOCK_2);
        registry.register(BlockCalculatorStructure.STRUCTURE_BLOCK_3);
        registry.register(BlockCalculatorController.CONTROLLER_1);
        registry.register(BlockCalculatorController.CONTROLLER_2);
        registry.register(BlockCalculatorController.CONTROLLER_3);
        registry.register(BlockComputingModule.INSTANCE);
        BlockMMController.CONTROLLERS.forEach(registry::register);
        BlockTransporter.getBlockMap().values().forEach(registry::register);
        ORES.forEach(registry::register);
        GameRegistry.registerTileEntity(TileManaLiquidizer.class, HerodotusUtils.rl("mana_liquidizer"));
        GameRegistry.registerTileEntity(TileCalculatorController.class, HerodotusUtils.rl("calculator_controller"));
        GameRegistry.registerTileEntity(TileComputingModule.class, HerodotusUtils.rl("computing_module"));
        GameRegistry.registerTileEntity(TileTransporter.class, HerodotusUtils.rl("transporter"));
    }
}
