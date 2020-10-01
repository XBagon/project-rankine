package com.cannolicatfish.rankine.blocks.pistoncrusher;


import com.cannolicatfish.rankine.init.ModBlocks;
import com.cannolicatfish.rankine.init.ModItems;
import com.cannolicatfish.rankine.recipe.PistonCrusherRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import static com.cannolicatfish.rankine.init.ModBlocks.PISTON_CRUSHER_CONTAINER;

public class PistonCrusherContainer extends Container {
    private TileEntity tileEntity;
    private final IInventory furnaceInventory;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private final IIntArray data;

    public PistonCrusherContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        this(windowId,world,pos,playerInventory,player, new Inventory(4),new IntArray(4));
    }

    public PistonCrusherContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IInventory furnaceInventoryIn,  IIntArray furnaceData) {
        super(PISTON_CRUSHER_CONTAINER, windowId);
        tileEntity = world.getTileEntity(pos);
        assertInventorySize(furnaceInventoryIn, 4);
        assertIntArraySize(furnaceData, 4);
        this.data = furnaceData;
        this.playerEntity = player;
        this.furnaceInventory = furnaceInventoryIn;
        this.playerInventory = new InvWrapper(playerInventory);

        this.addSlot(new Slot(furnaceInventory, 0, 56, 31));
        this.addSlot(new Slot(furnaceInventory, 1, 10,37));
        this.addSlot(new Slot(furnaceInventory, 2, 124,31));
        this.addSlot(new Slot(furnaceInventory, 3, 149,31));

        layoutPlayerInventorySlots(10, 70);

        this.trackIntArray(furnaceData);

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.PISTON_CRUSHER);
    }


    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 2 || index == 3) {
                if (!this.mergeItemStack(stack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else if (index != 1 && index != 0) {
                if (PistonCrusherRecipes.getInstance().getPrimaryResult(new ItemStack(stack.getItem())).getValue()[0] > 0f) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (AbstractFurnaceTileEntity.isFuel(stack)) {
                    if (!this.mergeItemStack(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 31) {
                    if (!this.mergeItemStack(stack, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 40 && !this.mergeItemStack(stack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    public int getOutputSlot() {
        return 2;
    }


    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled(int pixels)
    {
        int i = this.data.get(1);
        if(i == 0) i = 200;
        return this.data.get(0) * pixels / i;
    }


    @OnlyIn(Dist.CLIENT)
    public int getCookProgressScaled(int pixels)
    {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning()
    {
        return this.data.get(0) > 0;
    }

}