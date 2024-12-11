package net.rpm.disarmtripwire;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TripwireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.rpm.disarmtripwire.DisarmTripwire.MOD_ID;

public class DisarmTripwireHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void registerDisarmTripwireEvent(){
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            if(world.isClient){
                return ActionResult.PASS;
            }
            if(hand != Hand.MAIN_HAND){
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(hitResult.getBlockPos());
            Item mainHand = player.getStackInHand(hand).getItem();

            if (state.getBlock() instanceof TripwireBlock && mainHand instanceof ShearsItem) {
                LOGGER.info("Player right-clicked a tripwire with shears!");
                if (!state.get(TripwireBlock.DISARMED)) {
                    world.setBlockState(pos, state.with(TripwireBlock.DISARMED, true), 3);
                    LOGGER.info("Tripwire detached successfully!");
                    LOGGER.info("Final state after update: DISAMRED=={}", state.get(TripwireBlock.DISARMED));
                    return ActionResult.SUCCESS;
                }
                else {
                    return ActionResult.PASS;
                }

            }
            else{
                return ActionResult.PASS;
            }

        });
    }
    public static void registerEvent(){
        registerDisarmTripwireEvent();
    }
}