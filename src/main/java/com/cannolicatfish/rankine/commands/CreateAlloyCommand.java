package com.cannolicatfish.rankine.commands;

import com.cannolicatfish.rankine.items.alloys.AlloyData;
import com.cannolicatfish.rankine.items.alloys.AlloyItem;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class CreateAlloyCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("givealloy").requires((p_198496_0_) -> {
            return p_198496_0_.hasPermissionLevel(2);
        }).then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("item", ItemArgument.item()).executes((p_198492_0_) -> {
            return giveItem(p_198492_0_.getSource(), ItemArgument.getItem(p_198492_0_, "item"),StringArgumentType.getString(p_198492_0_, "alloy"), EntityArgument.getPlayers(p_198492_0_, "targets"), 1);
        }).then(Commands.argument("alloy", StringArgumentType.string()).executes((p_198493_0_) -> {
            return giveItem(p_198493_0_.getSource(), ItemArgument.getItem(p_198493_0_, "item"), StringArgumentType.getString(p_198493_0_, "alloy"), EntityArgument.getPlayers(p_198493_0_, "targets"), 1);
        }).then(Commands.argument("count", IntegerArgumentType.integer(1)).executes((p_198495_0_) -> {
            return giveItem(p_198495_0_.getSource(), ItemArgument.getItem(p_198495_0_, "item"), StringArgumentType.getString(p_198495_0_, "alloy"), EntityArgument.getPlayers(p_198495_0_, "targets"), IntegerArgumentType.getInteger(p_198495_0_, "count"));
        }))))));
    }

    private static int giveItem(CommandSource source, ItemInput itemIn, String data, Collection<ServerPlayerEntity> targets, int count) throws CommandSyntaxException {
        for(ServerPlayerEntity serverplayerentity : targets) {
            int i = count;

            while(i > 0) {
                int j = Math.min(itemIn.getItem().getMaxStackSize(), i);
                i -= j;
                ItemStack itemstack = itemIn.createStack(j, false);
                AlloyItem.addAlloy(itemstack,new AlloyData(data));
                boolean flag = serverplayerentity.inventory.addItemStackToInventory(itemstack);
                if (flag && itemstack.isEmpty()) {
                    itemstack.setCount(1);
                    ItemEntity itementity1 = serverplayerentity.dropItem(itemstack, false);
                    if (itementity1 != null) {
                        itementity1.makeFakeItem();
                    }

                    serverplayerentity.world.playSound((PlayerEntity)null, serverplayerentity.getPosX(), serverplayerentity.getPosY(), serverplayerentity.getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((serverplayerentity.getRNG().nextFloat() - serverplayerentity.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    serverplayerentity.container.detectAndSendChanges();
                } else {
                    ItemEntity itementity = serverplayerentity.dropItem(itemstack, false);
                    if (itementity != null) {
                        itementity.setNoPickupDelay();
                        itementity.setOwnerId(serverplayerentity.getUniqueID());
                    }
                }
            }
        }

        if (targets.size() == 1) {
            source.sendFeedback(new TranslationTextComponent("commands.give.success.single", count, itemIn.createStack(count, false).getTextComponent(), targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(new TranslationTextComponent("commands.give.success.single", count, itemIn.createStack(count, false).getTextComponent(), targets.size()), true);
        }

        return targets.size();
    }
}
