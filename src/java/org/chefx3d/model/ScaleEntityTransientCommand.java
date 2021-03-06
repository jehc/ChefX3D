/*****************************************************************************
 *                        Copyright Yumetech, Inc (c) 2006 - 2009
 *                               Java Source
 *
 * This source is licensed under the GNU LGPL v2.1
 * Please read http://www.gnu.org/copyleft/lgpl.html for more information
 *
 * This software comes with the standard NO WARRANTY disclaimer for any
 * purpose. Use it at your own risk. If there's a problem you get to fix it.
 *
 ****************************************************************************/

package org.chefx3d.model;

//External Imports

//Internal Imports
import java.util.HashSet;
import java.util.Arrays;

import org.chefx3d.util.DefaultErrorReporter;
import org.chefx3d.util.ErrorReporter;

/**
 * A command for transient scaling an entity.
 *
 * @author Ben Yarger
 * @version $Revision: 1.10 $
 */
public class ScaleEntityTransientCommand implements 
	Command, RuleDataAccessor, RuleBypassFlag {

    /** The model */
    private WorldModel model;

    /** The entity */
    private PositionableEntity entity;

    /** The position */
    private double[] pos;

    /** The scale */
    private float[] scale;
    
    /** The transaction ID */
    private int transactionID;
    
    /** The description of the <code>Command</code> */
    private String description;

    /** The flag to indicate transient status */
    private boolean transientState;

    /** The flag to indicate undoable status */
    private boolean undoableState;

    /** The ErrorReporter for messages */
    private ErrorReporter errorReporter;
    
    /** Is this a local command */
    private boolean local;
    
    /** A list of strings of class names of rules to ignore*/
    private HashSet<String> ignoreRuleList;
    
    /** Should the command die */
    private boolean shouldDie = false;
	
    /** The rule bypass flag, default is false */
    private boolean ruleBypassFlag;
    
    /**
     * Transient scale an entity.
     * 
     * @param model WorldModel
     * @param transID Transaction ID
     * @param entityID Entity ID
     * @param pos New entity position
     * @param scale New scale value
     */
    public ScaleEntityTransientCommand(
    		WorldModel model, 
			int transID, 
			PositionableEntity entity,
			double[] pos,
            float[] scale){
    	
    	this.model = model;
    	this.transactionID = transID;
    	this.entity = entity;
    	
    	this.pos = new double[3];
    	this.pos[0] = pos[0];
    	this.pos[1] = pos[1];
    	this.pos[2] = pos[2];
    	
    	this.scale = new float[3];
    	this.scale[0] = scale[0];
    	this.scale[1] = scale[1];
    	this.scale[2] = scale[2];
    	
    	local = true;
    	
    	init();
    }
    
    /**
     * Common initialization method.
     */
    private void init() {
        errorReporter = DefaultErrorReporter.getDefaultReporter();

        transientState = true;
        undoableState = false;
    }
    
    //---------------------------------------------------------------
    // Methods required by Command
    //---------------------------------------------------------------
    
    /**
     * Execute the command.
     */
	public void execute() {
		
		entity.setPosition(pos, transientState);
		
		entity.setProperty(
				Entity.DEFAULT_ENTITY_PROPERTIES, 
				PositionableEntity.SCALE_PROP, 
				scale, 
				transientState);

	}

    /**
     * Get the text description of this <code>Command</code>.
     */
	public String getDescription() {

		return description;
	}

    /**
     * Get the transactionID for this command.
     *
     * @return The transactionID
     */
	public int getTransactionID() {

		return transactionID;
	}

    /**
     * Is the command locally generated.
     *
     * @return Is local
     */
	public boolean isLocal() {

		return local;
	}

    /**
     * Get the transient state of this <code>Command</code>.
     */
	public boolean isTransient() {

		return transientState;
	}

    /**
     * Get the undo setting of this <code>Command</code>. true =
     * <code>Command</code> may be undone false = <code>Command</code> may
     * never undone
     */
	public boolean isUndoable() {

		return undoableState;
	}

    /**
     * Redo the effects of this command.
     */
	public void redo() {

		//Ignore
	}

    /**
     * Set the text description of this <code>Command</code>.
     */
	public void setDescription(String desc) {

		description = desc;
	}

    /**
     * Register an error reporter with the command instance
     * so that any errors generated can be reported in a nice manner.
     *
     * @param reporter The new ErrorReporter to use.
     */
	public void setErrorReporter(ErrorReporter reporter) {

        errorReporter = reporter;

        if(errorReporter == null)
            errorReporter = DefaultErrorReporter.getDefaultReporter();
	}

    /**
     * Set the local flag.
     *
     * @param isLocal Is this a local update
     */
	public void setLocal(boolean isLocal) {
		
		local = isLocal;
	}

    /**
     * Undo the effects of this command.
     */
	public void undo() {
		
		//Ignore
	}
	
	//---------------------------------------------------------------
	// Methods required by RuleDataAccessor
	//---------------------------------------------------------------

	/**
	 * Get the entity.
	 * 
	 * @return entity
	 */
	public Entity getEntity() {
		
		return entity;
	}

	/**
	 * Get the WorldModel
	 * 
	 * @return model
	 */
	public WorldModel getWorldModel() {
		
		return model;
	}

	//---------------------------------------------------------------
	// Class methods
	//---------------------------------------------------------------
	
	/**
	 * Get the position.
	 * 
	 * @param pos Entity position
	 */
	public void getPosition(double[] pos){
		
		pos[0] = this.pos[0];
		pos[1] = this.pos[1];
		pos[2] = this.pos[2];
	}
	
	/**
	 * Set the position.
	 * 
	 * @param pos New position
	 */
	public void setPosition(double[] pos){
		
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
		this.pos[2] = pos[2];
	}
	
	/**
	 * Get the scale
	 * 
	 * @param scale Entity scale
	 */
	public void getScale(float[] scale){
		
		scale[0] = this.scale[0];
		scale[1] = this.scale[1];
		scale[2] = this.scale[2];
	}
	
	/**
	 * Set the scale
	 * 
	 * @param scale New scale
	 */
	public void setScale(float[] scale){
		
		this.scale[0] = scale[0];
		this.scale[1] = scale[1];
		this.scale[2] = scale[2];
	}

	public HashSet<String> getIgnoreRuleList() {
        // TODO Auto-generated method stub
        return ignoreRuleList;
    }

    public void setIgnoreRuleList(HashSet<String> ignoreRuleList) {
        this.ignoreRuleList = ignoreRuleList;
    }

    public void resetToStart() {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Set the die state of the command. Setting this to true will
     * only cause the command to die if the rule engine execution
     * returns false.
     * 
     * @param die True to have command die and not execute
     */
	public void setCommandShouldDie(boolean die) {
		
		shouldDie = die;
	}

	/**
	 * Get the die value of the command.
	 * 
	 * @return True to have command die, false otherwise
	 */
	public boolean shouldCommandDie() {

		return shouldDie;
	}
	
	/**
	 * Compare external command to this one to see if they are the same.
	 * 
	 * @param externalCommand command to compare against
	 * @return True if the same, false otherwise
	 */
	public boolean isEqualTo(Command externalCommand) {
		
		if (externalCommand instanceof ScaleEntityTransientCommand) {
		
			if (((ScaleEntityTransientCommand)externalCommand).getTransactionID() == 
				this.transactionID) {
				
				return true;
			}
			
			double[] endPosition = new double[3];
			float[] endScale = new float[3];
			
			((ScaleEntityTransientCommand)externalCommand).getPosition(endPosition);
			((ScaleEntityTransientCommand)externalCommand).getScale(endScale);
			
			if (((ScaleEntityTransientCommand)externalCommand).getEntity() != this.entity) {
				
				return false;
			 	
			} else if (!Arrays.equals(endPosition, this.pos)) {
				
				return false;
				
			} else if (!Arrays.equals(endScale, this.scale)) {
				
				return false;
				
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Override object's equals method
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Command) {
			return isEqualTo((Command)obj);
		}
		
		return false;
	}
	
	//--------------------------------------------------------------------------
    // Methods required by RuleBypassFlag
    //--------------------------------------------------------------------------

    /**
     * Set the rule bypass flag value.
     *
     * @param ruleBypassFlag True to bypass rules, false otherwise
     */
    public void setBypassRules(boolean bypass){
        this.ruleBypassFlag = bypass;
    }

    /**
     * Get the rule bypass flag value.
     *
     * @return boolean True to bypass, false otherwise
     */
    public boolean bypassRules() {
        return ruleBypassFlag;
    }
}
