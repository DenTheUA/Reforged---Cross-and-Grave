package org.silvercatcher.reforged_cag.necromantic;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public abstract class NecromanticTransformation <T extends EntityLiving>{
	
	/**
	 * @return the cost to transform a target, can be a constant value
	 * or change, depending on parameters
	 */
	public abstract int getTransformationCost(
			EntityPlayer necromancer, T target);
	
	/** 
	 * @return true, if transformation can happen
	 * please use ChatMessages to give feedback to the necromancer
	 */
	public boolean checkTransformable(
			EntityPlayer necromancer, T creature) {
		return true;
	}
	
	/**
	 * Overwrite this method to manipulate a slave when transforming,
	 * for example to change AI tasks, change health, apply other effects
	 * or even change it into a different entity.
	 * 
	 * @return the transformed slave
	 */
	public EntityLiving transform(
			EntityPlayer master, T slave) {
		
		slave.targetTasks.taskEntries.clear();
		slave.targetTasks.addTask(0, new EntityAINecromancerControlled(slave));
		return slave;
	}
	
	protected NecromanticMinionProperties extendProperties(EntityPlayer master, EntityLiving slave) {
		
		NecromanticMinionProperties properties =
				new NecromanticMinionProperties(master);
		slave.registerExtendedProperties(NecromanticMinionProperties.key, properties);
		return properties;
	}
}
