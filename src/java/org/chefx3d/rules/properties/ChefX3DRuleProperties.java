/*****************************************************************************
 *                        Copyright Yumetech, Inc (c) 2006 - 2010
 *                               Java Source
 *
 * This source is licensed under the GNU LGPL v2.1
 * Please read http://www.gnu.org/copyleft/lgpl.html for more information
 *
 * This software comes with the standard NO WARRANTY disclaimer for any
 * purpose. Use it at your own risk. If there's a problem you get to fix it.
 *
 ****************************************************************************/

package org.chefx3d.rules.properties;

//External Imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Internal Imports

/**
 * Defines all ChefX3D rule properties. Project specific properties and their
 * rules exist only in that specific project. These are universal properties
 * shared by all projects.
 *
 * @author Ben Yarger
 * @version $Revision: 1.132 $
 */
public class ChefX3DRuleProperties {

    /**
     * Singleton reference that causes defaultValueMap to be initialized.
     */
    private static ChefX3DRuleProperties ruleProperties =
        new ChefX3DRuleProperties();

    /**
     * Default constructor
     */
    private ChefX3DRuleProperties() {

        initializeDefaultValues();
    }

    //---------------------------------------------------------------
    // Default Value Map (Rule property key and default value map
    //
    // Please add these in the same order that you add them to the
    // sections below so we can easily find them.
    //---------------------------------------------------------------
    private static HashMap<String, Object> defaultValueMap;

    //---------------------------------------------------------------
    // Fixed constants
    //---------------------------------------------------------------

    /** The maximum allowable wall height 2.4384 m = 96" or 8' */
    public static final double MAXIMUM_WALL_HEIGHT = 2.4384;

    /** Reserved relationship name, None */
    public static final String RESERVED_NONE_RELATIONSHIP = "None";

    /** Wall constant reserved relationship name */
    public static final String WALL_CONST = "wall";

    /** Floor constant reserved relationship name */
    public static final String FLOOR_CONST = "floor";

    //---------------------------------------------------------------
    // Enumerated objects - used by specific properties for values
    //---------------------------------------------------------------

    /**
     * Possible values specifying appropriate adjustment axis
     * targets. Generic enum for multiple use cases.
     */
    public enum TARGET_ADJUSTMENT_AXIS {
        XAXIS,
        YAXIS,
        ZAXIS,
        NONE};

    /**
     * Possible values for axis specific changes that require a value.
     */
    public enum ADJUSTMENT_AXIS {
        XAXIS,
        YAXIS,
        ZAXIS};

    /** Possible values denoting restriction of scaling/size change controls */
    public enum SCALE_RESTRICTION_VALUES {
        XAXIS,
        YAXIS,
        ZAXIS,
        XYPLANE,
        XZPLANE,
        YZPLANE,
        UNIFORM,
        NONE};

    /** Possible values denoting restriction of movement to a specific plane */
    public enum MOVEMENT_PLANE_RESTRICTION_VALUES {
        XYPLANE,
        YZPLANE,
        XZPLANE,
        NONE};

    /** Possible values denoting relationship modifier */
    public enum RELATIONSHIP_MODIFIER_VALUES {
        LESS_THAN,
        GREATER_THAN,
        LESS_THAN_OR_EQUAL_TO,
        GREATER_THAN_OR_EQUAL_TO,
        EQUAL_TO,
        NOT_EQUAL_TO,
        NONE};

    /** Possible values specifying initial relative product positioning */
    public enum RELATIVE_GENERAL_POSITION {
        FRONT,
        BACK,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        CENTER,
        CENTER_HORIZONTAL,
        CENTER_VERTICAL,
        FRONT_HORIZONTAL,
        FRONT_VERTICAL,
        BACK_HORIZONTAL,
        BACK_VERTICAL,
        LEFT_HORIZONTAL,
//      LEFT_VERTICAL, -- Would never lock left
        RIGHT_HORIZONTAL,
//      RIGHT_VERTICAL, -- Would never lock right
//      TOP_HORIZONTAL, -- would never lock top
        TOP_VERTICAL,
//      BOTTOM_HORIZONTAL, -- would never lock bottom
        BOTTOM_VERTICAL};

    /**
     * Possible values for axis specific scale model changes.
     */
    public enum SCALE_CHANGE_MODEL_AXIS_VALUES {
        XAXIS,
        YAXIS,
        ZAXIS};

    /**
     * Orientation for auto add as either positive or negative. Default if for
     * cases that require no orientation consideration.
     */
    public enum ORIENTATION {POSITIVE, NEGATIVE, DEFAULT};

    /**
     * Possible orientation states that any given entity can have.
     */
    public enum ORIENTATION_STATE {
        LEFT,
        RIGHT,
        LEFT_ONLY,
        RIGHT_ONLY};

    /**
     * Collision Alignment Methods
     */
    public enum COLLISION_ALIGNMENT_METHOD_VALUES {
        CENTER,
        NEAR_BOUNDS,
        FAR_BOUNDS};

    /**
     * Snapping style to use.
     */
    public enum PROX_SNAP_STYLE {
        CENTER,
        EDGE };

    /**
     * If the heights are not aligned because they are within tolerance, then
     * we want to track if it is above or below.
     */
    public enum PROX_SNAP_HEIGHT_OPT {
        EVEN,
        ABOVE,
        BELOW};

    /** Edge face snap axis orientation options. */
    public enum EDGE_FACE_SNAP_ADJ_AXIS {
        XAXIS,
        YAXIS};

    /**
     * Auto add conditions dictating how the auto add operations should be
     * evaluated.
     */
    public enum AUTO_ADD_CONDITION {
        FIRST_OR_NONE,      // Add first successful and proceed or add none
        FIRST_OR_FAIL,      // Add first successful, fail if none add
        ADD_ALL_POSSIBLE,   // Add all products in auto add list or none
        ADD_ALL_OR_FAIL     // Add all products, fail if any fail
        };

    /**
     * Auto add end placement option dictating which end, or both the product
     * should be added onto.
     */
    public enum END_OPTION {
        POSITIVE,
        NEGATIVE,
        BOTH
        };

    //---------------------------------------------------------------
    // Internal use only properties - SHOULD NEVER BE SET BY USER!!
    //---------------------------------------------------------------

    /** Internal property to determine the end product's orientation */
    public static final String AUTO_ADD_END_ORIENTATION = "CX.aaEndOrient";

    /** Exists and is true only if the entity was auto added to the scene */
    public static final String IS_AUTO_ADD_PRODUCT = "CX.isAutoAdd";

    /** Flags when the permanent parent rule is set */
    public static final String PERMANENT_PARENT_SET = "CX.permParentSet";

    /** Flags transient orientation as left or right */
    public static final String LIVE_STATE_ORIENTATION = "CX.liveStateOrient";

    /** Initial parent entity id from add command */
    public static final String INITAL_ADD_PARENT = "CX.initAddParent";

    /** If true the entity has not been added to the scene yet */
    public static final String SHADOW_ENTITY_FLAG = "Shadow State";

    /**
     * Side pockets the entity id of the target of the entity's prox snap.
     * Used by ProximityPositionSnapRule.
     */
    public static final String PROXIMITY_SNAP_TARGET_ENTITY_ID = "CX.prxTgtID";

    /**
     * Side pockets the state of the entity's position above or below the
     * proximity snap target. Used by ProximityPositionSnapRule.
     */
    public static final String PROXIMITY_HEIGHT_SNAP_ABOVE_BELOW = "CX.prxHtAB";

    /** Is the product a complex product type */
    public static final String IS_COMPLEX_PRODUCT = "CX.cpxProd";

    /** Is the product a sub part of the complex product */
    public static final String IS_COMPLEX_PRODUCT_SUBPART = "CX.cpxSubPart";
    
    /** Last good miter spine to revert back to */
    public static final String MITRE_CUT_SPINE_LAST_GOOD = "CX.mitreSpineLastGood";
    
    /** Last good miter spine visible values to revert back to */
    public static final String MITRE_CUT_VISIBLE_LAST_GOOD = "CX.mitreVisibleLastGood";
    
    /** Last good miter enable values to revert back to */
    public static final String MITRE_CUT_ENABLE_LAST_GOOD = "CX.mitreEnableLastGood";
    
    /** Side pocketed scale attach targets */
    public static final String MOVEMENT_SCALE_ATTACHED_TARGETS = "CX.moveSclAttchProd";
    
    /** Possible parent used by genPos */
    public static final String POSSIBLE_PARENT = "POSSIBLE_PARENT";

    /** The complete list of internal only property keys */
    public static List<String> INTERNAL_PROP_LEYS;
    static {
        INTERNAL_PROP_LEYS = new ArrayList<String>();
        INTERNAL_PROP_LEYS.add(AUTO_ADD_END_ORIENTATION);
        INTERNAL_PROP_LEYS.add(IS_AUTO_ADD_PRODUCT);
        INTERNAL_PROP_LEYS.add(PERMANENT_PARENT_SET);
        INTERNAL_PROP_LEYS.add(LIVE_STATE_ORIENTATION);
        INTERNAL_PROP_LEYS.add(INITAL_ADD_PARENT);
        INTERNAL_PROP_LEYS.add(SHADOW_ENTITY_FLAG);
        INTERNAL_PROP_LEYS.add(PROXIMITY_SNAP_TARGET_ENTITY_ID);
        INTERNAL_PROP_LEYS.add(PROXIMITY_HEIGHT_SNAP_ABOVE_BELOW);
        INTERNAL_PROP_LEYS.add(IS_COMPLEX_PRODUCT);
        INTERNAL_PROP_LEYS.add(IS_COMPLEX_PRODUCT_SUBPART);
        INTERNAL_PROP_LEYS.add(MITRE_CUT_SPINE_LAST_GOOD);
        INTERNAL_PROP_LEYS.add(MITRE_CUT_VISIBLE_LAST_GOOD);
        INTERNAL_PROP_LEYS.add(MITRE_CUT_ENABLE_LAST_GOOD);
        INTERNAL_PROP_LEYS.add(MOVEMENT_SCALE_ATTACHED_TARGETS);
        INTERNAL_PROP_LEYS.add(POSSIBLE_PARENT);
        INTERNAL_PROP_LEYS.add("PositionableEntity.boundsBorder");
        INTERNAL_PROP_LEYS.add("Entity.kitID");
        INTERNAL_PROP_LEYS.add("Entity.templateID");
    }

    //---------------------------------------------------------------
    // Object/Entity properties/Misc
    //---------------------------------------------------------------

    /**
     * Holds a boolean where true means the Object/Entity is editable.
     * False means the Object/Entity is not editable. This is the first
     * check performed before any others. If it is false no further action
     * is generally allowed.
     */
    public static final String IS_EDITABLE_PROP = "CX.isEditable";

    /** Defines the maximum size for an Object/Entity. */
    public static final String MAXIMUM_OBJECT_SIZE_PROP = "CX.maxObjSize";

    /** Defines the minimum size for an Object/Entity. */
    public static final String MINIMUM_OBJECT_SIZE_PROP = "CX.minObjSize";

    /**
     * Defines the depth to put the entity inside the parent entity.
     * At this time, used for doors and windows to help place them
     * in the wall correctly.
     */
    public static final String EMBED_DEPTH_PROP = "CX.embedDepth";

    /** Prevent entity from being deleted if set to true */
    public static final String CAN_DELETE_PROP = "CX.canDel";

    /** Prevents wall edge snap from being applied to product */
    public static final String IGNORE_WALL_EDGE_SNAP = "CX.noWallEdgeSnap";

    /** Global depth offset value, can be overridden by subsequent rules */
    public static final String GLOBAL_DEPTH_OFFSET = "CX.glblDpthOffset";

    /** Single Selection / Single entity of this type Selected at a time */
    public static final String SINGLE_SELECTION_ONLY = "CX.singleSel";

    //---------------------------------------------------------------
    // Movement properties
    //---------------------------------------------------------------

    /** Prevents product movement outside of the segment */
    public static final String MOVEMENT_RESTRICTED_TO_BOUNDARY = "CX.resToBoundary";

    /**
     * If exists, and not set to none, movement is restricted to a specific
     * plane. Use of this property results in movement data in the illegal
     * axis being removed from the command.
     */
    public static final String MOVEMENT_RESTRICTED_TO_PLANE_PROP = "CX.mvResToPlane";

    /** If exists and not false, then the movement is based on snaps and they should be used to update the position. */
    public static final String MOVEMENT_USES_SNAPS_PROP = "CX.mvUseSnap";

    /** Movement of entity uses absolute snaps. */
    public static final String MOVEMENT_USES_ABSOLUTE_SNAPS_PROP = "CX.mvUseAbsSnap";

    /** Absolute snap values for movement along x axis. */
    public static final String MOVEMENT_ABSOLUTE_X_AXIS_SNAP_PROP = "CX.mvAbsXAxisSnap";

    /** Absolute snap values for movement along y axis. */
    public static final String MOVEMENT_ABSOLUTE_Y_AXIS_SNAP_PROP = "CX.mvAbsYAxisSnap";

    /** Absolute snap values for movement along z axis. */
    public static final String MOVEMENT_ABSOLUTE_Z_AXIS_SNAP_PROP = "CX.mvAbsZAxisSnap";

    /** Movement of entity uses incremental snaps. */
    public static final String MOVEMENT_USES_INCREMENTAL_SNAPS_PROP = "CX.mvUseIncSnap";

    /** Incremental snap value for movement along x axis. */
    public static final String MOVEMENT_INCREMENTAL_X_AXIS_SNAP_PROP = "CX.mvIncXAxisSnap";

    /** Incremental snap value for movement along y axis. */
    public static final String MOVEMENT_INCREMENTAL_Y_AXIS_SNAP_PROP = "CX.mvIncYAxisSnap";

    /** Incremental snap value for movement along z axis. */
    public static final String MOVEMENT_INCREMENTAL_Z_AXIS_SNAP_PROP = "CX.mvIncZAxisSnap";

    /** Incremental snap values for special case movement along x axis. */
    public static final String MOVEMENT_INCREMENTAL_X_AXIS_SPECIAL_CASE_SNAP_PROP = "CX.mvIncXAxisSCSnap";

    /** Incremental snap values for special case movement along y axis. */
    public static final String MOVEMENT_INCREMENTAL_Y_AXIS_SPECIAL_CASE_SNAP_PROP = "CX.mvIncYAxisSCSnap";

    /** Incremental snap values for special case movement along z axis. */
    public static final String MOVEMENT_INCREMENTAL_Z_AXIS_SPECIAL_CASE_SNAP_PROP = "CX.mvIncZAxisSCSnap";

    /** If true the snap is calculated from the floor. */
    public static final String SNAP_RELATIVE_TO_FLOOR = "CX.snapFrmFloor";

    /** Snap to a product or product classification */
    public static final String SNAP_TO_CLASS_PROP = "CX.snapToClass";

    /** Style of snapping to use, edge or center */
    public static final String PROX_SNAP_STYLE_PROP = "CX.proxSnapStyle";

    /** Incremental x axis snap exceptions specified by index */
    public static final String INC_X_SNAP_EXCLUDE_PROP = "CX.incXExcl";

    /** Incremental y axis snap exceptions specified by index */
    public static final String INC_Y_SNAP_EXCLUDE_PROP = "CX.incYExcl";

    /** Incremental z axis snap exceptions specified by index */
    public static final String INC_Z_SNAP_EXCLUDE_PROP = "CX.incZExcl";

    /** Ignore move restrictions on add */
    public static final String MOVEMENT_IGNORE_RESTRICT_ON_ADD = "CX.ignoreMoveResOnAdd";

    /** Restrict snaps to model collisions only */
    public static final String SNAP_TO_MODEL_ONLY = "CX.snapToModelOnly";

    /** Flags free floating rule execution for movement and scale */
    public static final String FREE_FLOATING_CONSTRAINED = "CX.freeFloat";

    /** If exists and is true, snaps as indicated by the associated index are 'sticky' */
    public static final String USES_STICKY_SNAPS_PROP = "CX.stickySnap";

    /** Used to specify the snap indices that are 'sticky' */
    public static final String STICKY_SNAP_INDEX_PROP = "CX.stickySnapIndex";

    /** Movement scales attached products; type is boolean, default is false */
    public static final String MOVEMENT_SCALES_ATTACHED_PRODUCTS = "CX.mvScalesAttached";



    //---------------------------------------------------------------
    // Rotation properties
    //---------------------------------------------------------------

    /** If doesn't exist or is set to true, the object can rotate, otherwise false. */
    public static final String CAN_ROTATE_PROP = "CX.canRotate";

    /** If exists, and not set to NONE, rotation is restricted to axis assigned. Uses Enum TARGET_ADJUSTMENT_AXIS. */
    public static final String ROTATION_AXIS_RESTRICTION_PROP = "CX.rotAxisRes";

    /** If exists, the float value is used as an incremental adjustment to the rotation of the object. Expects values to be in radians. */
    public static final String SNAP_TO_ROTATION_INCREMENT_PROP = "CX.snapToRotInc";

    /** If exists, the float[] values are used to adjust the rotation to a fixed number of positions. Expects values to be in radians. */
    public static final String SNAP_TO_ROTATION_ABSOLUTE_VALUE_PROP = "CX.snapToRotAbsValue";




    //---------------------------------------------------------------
    // Scale/Size properties
    //---------------------------------------------------------------

    /** If doesn't exist or is set to true, the object can scale, otherwise false. */
    public static final String CAN_SCALE_PROP = "CX.canScale";

    /**
     * Restricts scaling based on Enum SCALE_RESTRICTION_VALUES. If not set,
     * or NONE specified, scaling/resizing is allowed on all axis within the
     * confines of the maximum and minimum size values.
     */
    public static final String SCALE_RESTRICTION_PROP = "CX.scaleRes";

    /**
     * Restricts scaling to bounds of parent object.
     */
    public static final String SCALE_BOUND_BY_PARENT_PROP = "CX.parentBoundsRes";

    /** If exists and not false, then the scale is based on snaps and they should be used to update the scale. */
    public static final String SCALE_USES_SNAPS_PROP = "CX.scUseSnap";

    /** Scale of entity uses absolute snaps. */
    public static final String SCALE_USES_ABSOLUTE_SNAPS_PROP = "CX.scUseAbsSnap";

    /** Absolute snap values for scale along x axis. */
    public static final String SCALE_ABSOLUTE_X_AXIS_SNAP_PROP = "CX.scAbsXAxisSnap";

    /** Absolute snap values for scale along y axis. */
    public static final String SCALE_ABSOLUTE_Y_AXIS_SNAP_PROP = "CX.scAbsYAxisSnap";

    /** Absolute snap values for scale along z axis. */
    public static final String SCALE_ABSOLUTE_Z_AXIS_SNAP_PROP = "CX.scAbsZAxisSnap";

    /** Scale of entity uses incremental snaps. */
    public static final String SCALE_USES_INCREMENTAL_SNAPS_PROP = "CX.scUseIncSnap";

    /** Incremental snap value for scale along x axis. */
    public static final String SCALE_INCREMENTAL_X_AXIS_SNAP_PROP = "CX.scIncXAxisSnap";

    /** Incremental snap value for scale along y axis. */
    public static final String SCALE_INCREMENTAL_Y_AXIS_SNAP_PROP = "CX.scIncYAxisSnap";

    /** Incremental snap value for scale along z axis. */
    public static final String SCALE_INCREMENTAL_Z_AXIS_SNAP_PROP = "CX.scIncZAxisSnap";

    //---------------------------------------------------------------
    // Relationship properties
    //---------------------------------------------------------------

    /** The classification type of the product */
    public static final String CLASSIFICATION_PROP = "CX.class";

    /** Describes a relationship to a product or product classification */
    public static final String RELATIONSHIP_CLASSIFICATION_PROP = "CX.relClass";

    /** Describes the number required for the interaction */
    public static final String RELATIONSHIP_AMOUNT_PROP = "CX.relAmt";

    /** Modifies the number required with ==, >, <, <=, >= etc */
    public static final String RELATIONSHIP_MODIFIER_PROP = "CX.relMod";

    /** Sets the initial relative position between product and parent */
    public static final String GENERAL_POSITION_PROP = "CX.genPos";
    
    /** 
     * Specifies the direction of the gen pos calculation child -> parent or
     * parent -> child.
     */
    public static final String GENERAL_POSITION_DIRECTION_PROP = "CX.genPosDir";

    /** Specifies the amount of border between product and parent bottom */
    public static final String BOTTOM_POS_BUFFER_PROP = "CX.botPosBuff";

    /** Specifies the amount of border between product and parent top */
    public static final String TOP_POS_BUFFER_PROP = "CX.topPosBuff";

    /** Specifies the amount of border between product and parent left */
    public static final String LEFT_POS_BUFFER_PROP = "CX.leftPosBuff";

    /** Specifies the amount of border between product and parent right */
    public static final String RIGHT_POS_BUFFER_PROP = "CX.rightPosBuff";

    /** Specifies the amount of border between product and parent front */
    public static final String FRONT_POS_BUFFER_PROP = "CX.frontPosBuff";

    /** Specifies the amount of border between product and parent back */
    public static final String BACK_POS_BUFFER_PROP = "CX.backPosBuff";

    /** Specifies the amount of horizontal offset from parent center */
    public static final String CENTER_HORIZONTAL_POS_BUFF_PROP = "CX.cenHorPosBuff";

    /** Specifies the amount of vertical offset from parent center */
    public static final String CENTER_VERTICAL_POS_BUFF_PROP = "CX.cenVertPosBuff";

    /** Specifies the amount of depth offset from parent center */
    public static final String CENTER_DEPTH_POS_BUFF_PROP = "CX.cenDepthPosBuff";

    /** Specifies classifications to replace if in collision with */
    public static final String REPLACE_PROD_CLASS_PROP = "CX.repProd";

    /** Forcibly add the parent to the collision entity list */
    public static final String PARENT_AS_COLLISION_ALTERNATE = "CX.parentColAltOpt";

    /** Enable multi collision relative positioning */
    public static final String USE_MULTI_COLLISION_POSITIONING = "CX.useMultColPos";

    //---------------------------------------------------------------
    // Auto placement
    //---------------------------------------------------------------

        // ---- Shared ----

    /** Specifies that the product should be loaded without a modelURL */
    public static final String NO_MODEL_PROP = "CX.noModel";

    /** Ignore the restrictive deletion check for auto add entities */
    public static final String IGNORE_AUTO_ADD_DEL_RESTRICTION = "CX.ignoreDelRes";

        // ---- Auto Add By Span ----

    /** Flag to add auto add by span */
    public static final String AUTO_ADD_BY_SPAN_USE = "CX.autoAddSpan";

    /** Specifies the product id's to auto place by span. */
    public static final String AUTO_ADD_BY_SPAN_PROP = "CX.autoPlObj";

    /** X, Y, or Z, determines the axis along which to auto add product.  */
    public static final String AUTO_ADD_BY_SPAN_AXIS = "CX.autoAddAxis";

    /** Auto add step size for matched index.<br>
     * This is the interval at which an auto add instance should be placed.<br>
     * <br>Using the existing step management algorithm, swap out this value for
     * the step such that the number added will never exceed the step size
     * required for safe placement and such that the total number will be
     * evenly spaced over the maximum distance, which is:
     * (parent dimension - autoAdd*Offset values).
     */
    public static final String AUTO_ADD_BY_SPAN_STEP_SIZE = "CX.autoAddStepSize";

    /**
     * Force fit auto add products if collisions prevent it at the specified
     * step interval.
     */
    public static final String AUTO_ADD_BY_SPAN_FORCE_FIT = "CX.autoForceFit";

    /**
     * Offset from negative axis direction end to place the end auto
     * placement from.
     */
    public static final String AUTO_ADD_BY_SPAN_NEG_OFFSET = "CX.autoAddNegOffset";

    /**
     * Offset from positive axis direction end to place the end auto placement
     * from.
     */
    public static final String AUTO_ADD_BY_SPAN_POS_OFFSET = "CX.autoAddPosOffset";

    /** int - The number of auto-add items required to be a valid action */
    public static final String AUTO_ADD_BY_SPAN_MIN_REQUIRED = "CX.autoAddMinRequired";

    /** Conditions affecting the calculation of auto span placement. */
    public static final String AUTO_ADD_BY_SPAN_CONDITIONS = "CX.autoAddCond";

        // ---- Auto Add By Collision ----

    /**
     * If set true, auto add is based on collision objects of the parent.
     * The collision position is used to determine placement.
     */
    public static final String AUTO_ADD_BY_COL_USE = "CX.autoAddByCol";

    /** Specifies the product id's to auto place by collision. */
    public static final String AUTO_ADD_COL_PROP = "CX.autoAddColObj";

    /** X, Y, or Z, determines the axis along which to auto add product.  */
    public static final String AUTO_ADD_COL_AXIS = "CX.autoAddColAxis";

    /** Auto add step size for matched index.<br>
     * This is the interval at which an auto add instance should be placed.<br>
     * <br>Using the existing step management algorithm, swap out this value for
     * the step such that the number added will never exceed the step size
     * required for safe placement and such that the total number will be
     * evenly spaced over the maximum distance, which is:
     * (parent dimension - autoAdd*Offset values). If the distance
     * between auto place by collision items is greater than that specified by
     * step size then that will register as an illegal installation because it
     * is unsafe. In this case, do not add the parent or any of the children.
     */
    public static final String AUTO_ADD_COL_STEP_SIZE = "CX.autoAddColStepSize";

    /**
     * Force fit auto add products if collisions prevent it at the specified
     * step interval.
     */
    public static final String AUTO_ADD_COL_FORCE_FIT = "CX.autoAddColForceFit";

    /** int - The number of auto-add items required to be a valid action. */
    public static final String AUTO_ADD_COL_MIN_REQUIRED = "CX.autoAddColMinReq";

    /** Conditions affecting the calculation of auto add collision placement. */
    public static final String AUTO_ADD_COL_CONDITIONS = "CX.autoAddColCond";

        // ---- Auto Add By Position (auto add N) ----

    /**
     * If set true, auto add is based on collision objects of the parent.
     * The collision position is used to determine placement.
     */
    public static final String AUTO_ADD_N_UNITS_PROP_USE = "CX.addNUnits";

    /** Specifies the product id's to auto place by position. */
    public static final String AUTO_ADD_N_PROP = "CX.addNObj";

    /** Auto add n units positions along x axis */
    public static final String AUTO_ADD_N_X_POS_PROP = "CX.addNXPos";

    /** Auto add n units positions along y axis */
    public static final String AUTO_ADD_N_Y_POS_PROP = "CX.addNYPos";

    /** Auto add n units positions along z axis */
    public static final String AUTO_ADD_N_Z_POS_PROP = "CX.addNZPos";

    /** int - The number of auto-add items required to be a valid action. */
    public static final String AUTO_ADD_N_MIN_REQUIRED = "CX.addNMinReq";

    /**
     * Conditions affecting the calculation of auto add by position placement.
     */
    public static final String AUTO_ADD_N_CONDITIONS = "CX.addNCond";

        // ---- Auto Add Ends ----

    /**
     * If set true, auto add is based on end placement of objects of the parent.
     * The end positions are used to determine placement.
     */
    public static final String AUTO_ADD_ENDS_PLACEMENT_USE = "CX.autoAddEnds";

    /** Specifies the product id's to auto place on ends. */
    public static final String AUTO_ADD_ENDS_PROP = "CX.autoAddEndsObj";

    /** X, Y, or Z, determines the axis along which to auto add product.  */
    public static final String AUTO_ADD_ENDS_AXIS = "CX.autoAddEndsAxis";

    /**
     * Offset from positive axis direction end to place the end auto placement
     * from.
     */
    public static final String AUTO_ADD_ENDS_POS_OFFSET = "CX.autoAddEndsPosOffset";

    /**
     * Offset from negative axis direction end to place the end auto
     * placement from.
     */
    public static final String AUTO_ADD_ENDS_NEG_OFFSET = "CX.autoAddEndsNegOffset";

    /** Specifies if one end or the other, or both should be added to. */
    public static final String AUTO_ADD_ENDS_OPTION = "CX.autoAddEndsOpt";

    /**
     * Conditions affecting the calculation of auto add by position placement.
     */
    public static final String AUTO_ADD_ENDS_CONDITIONS = "CX.autoAddEndsCond";

        // ---- Add Invisible Children ----

    /** Flag to add invisible children */
    public static final String AUTO_ADD_INVISIBLE_CHILDREN_USE = "CX.autoAddInv";

    /** Specifies the product ids to add along with parent */
    public static final String AUTO_ADD_INVISIBLE_CHILDREN_PROP = "CX.invisChldrn";

    //---------------------------------------------------------------
    // Nearest neighbor adjustments
    //---------------------------------------------------------------

    /** boolean - identifies that the bounds adjustment should be considered with
     *  the nearest neighbor case. */
    public static final String USE_NEAREST_NEIGHBOR_BOUNDS_ADJ = "CX.useNNAdj";

    /** float - X axis bounds adjustment */
    public static final String NEAREST_NEIGHBOR_X_AXIS_ADJ = "CX.nnXAxisAdj";

    /** float - Y axis bounds adjustment */
    public static final String NEAREST_NEIGHBOR_Y_AXIS_ADJ = "CX.nnYAxisAdj";

    /** float - Z axis bounds adjustment */
    public static final String NEAREST_NEIGHBOR_Z_AXIS_ADJ = "CX.nnZAxisAdj";

    //---------------------------------------------------------------
    // Positioning properties
    //---------------------------------------------------------------

    /**
     * Specifies the maximum height the top edge of the item can occupy.
     * Can be used to prevent unsafe installation over certain height
     * and prevent stacking of more than n items based on non-adjustable
     * heights if used correctly in conjunction with relationship rules.
     *
     * type: float
     * default: 0
     */
    public static final String MAXIMUM_HEIGHT_PLACE_PROP = "CX.maxHeight";

    /**
     * Specifies the minimum height the bottom edge of the item can occupy.
     * Can be used to prevent unsafe installation under a certain height, such
     * as cabinets that should be installed above counter tops.
     *
     * type: float
     * default: 0
     */
    public static final String MINIMUM_HEIGHT_PLACE_PROP = "CX.minHeight";

    /**
     * Specifies the collision objects to stack with if collision is found.
     */
    public static final String STACK_PROP = "CX.stack";

    /**
     * Child must fit inside parent at all times. Should not be used for
     * anything that attaches to a wall. Only meant for product inside of
     * product interactions.
     */
    public static final String MUST_FIT_INSIDE_PARENT = "CX.fitInParent";

    /**
     * Child should be added inside the parent.  Only meant for product
     * inside of product interactions.  BOOLEAN value.
     */
    public static final String PLACED_INSIDE_PARENT = "CX.insideParent";

    //---------------------------------------------------------------
    // Auto SKU Change properties
    //---------------------------------------------------------------

    /** Specifies if auto sku adjustment should occur */
    public static final String PROD_SCALE_CHANGE_SKU_PROP = "CX.SKUChange";

    /** Specifies SKU step matched x axis values */
    public static final String PROD_SCALE_SKU_STEP_X_PROP = "CX.SKUStepX";

    /** Specifies SKU step matched y axis values */
    public static final String PROD_SCALE_SKU_STEP_Y_PROP = "CX.SKUStepY";

    /** Specifies SKU step matched z axis values */
    public static final String PROD_SCALE_SKU_STEP_Z_PROP = "CX.SKUStepZ";

    /** Specifies SKU scale step sizes x axis */
    public static final String PROD_SCALE_SIZE_STEP_X_PROP = "CX.SKUSizeX";

    /** Specifies SKU scale step sizes y axis */
    public static final String PROD_SCALE_SIZE_STEP_Y_PROP = "CX.SKUSizeY";

    /** Specifies SKU scale step sizes z axis */
    public static final String PROD_SCALE_SIZE_STEP_Z_PROP = "CX.SKUSizeZ";

    /** Specifies SKU step descriptions matching x axis step */
    public static final String PROD_SCALE_DESC_STEP_X_PROP = "CX.SKUDescX";

    /** Specifies SKU step descriptions matching y axis step */
    public static final String PROD_SCALE_DESC_STEP_Y_PROP = "CX.SKUDescY";

    /** Specifies SKU step descriptions matching z axis step */
    public static final String PROD_SCALE_DESC_STEP_Z_PROP = "CX.SKUDescZ";

    //---------------------------------------------------------------
    // Auto scale to fit (Hanger Bar example)
    //---------------------------------------------------------------

    /** Specifies auto scale span rule should occur */
    public static final String SPAN_OBJECT_PROP = "CX.spanObjs";

    /** Specifies offset from parent to use when auto span positioning */
    public static final String SPAN_OBJECT_DEPTH_OFFSET_PROP =
        "CX.spanDpthOfst";

    /**
     * Flags entity as being able to place on auto span object and become
     * a child of it. Default is false, cannot become child.
     */
    public static final String CAN_PLACE_ON_SPAN_OBJECT = "CX.plOnSpan";

    //---------------------------------------------------------------
    // Collision placement position rules
    //---------------------------------------------------------------

    /** Flags rule for execution */
    public static final String COLLISION_POSITION_REQUIREMENTS = "CX.colPosReq";

    /** Are we in a multizone case */
    public static final String COLLISION_POSITION_MULTI_ZONE_REQUIREMENTS = "CX.colPosReqMZ";

    /** X axis position values index ordered to match the relationship and amt order */
    public static final String COLLISION_POSITION_X_AXIS_VALUES = "CX.colPosX";

    /** Y axis position values index ordered to match the relationship and amt order */
    public static final String COLLISION_POSITION_Y_AXIS_VALUES = "CX.colPosY";

    /** Z axis position values index ordered to match the relationship and amt order */
    public static final String COLLISION_POSITION_Z_AXIS_VALUES = "CX.colPosZ";

    /** [0 - 1] inclusive tolerance values index ordered to match relationship
     * and amount order
     */
    public static final String COLLISION_POSITION_TOLERANCE = "CX.colTol";

    /** Axis allowed for adjustment of collision cases to align them for
     * placement of the product.
     */
    public static final String COLLISION_TARGET_ADJUSTMENT_AXIS = "CX.colAdjAxis";

    /** When moving the item do we align to the center of the object or make
     * bounds touch.   CENTER, NEAR_BOUNDS, FAR_BOUNDS
     */
    public static final String COLLISION_ALIGNMENT_METHOD = "CX.colAlgnMtd";

    //---------------------------------------------------------------
    // Parent rules
    //---------------------------------------------------------------

    /** Flag permanent parent rule for execution */
    public static final String USES_PERMANENT_PARENT = "CX.usePermParent";

    /** Specifies the possible classifications that can be parented to */
    public static final String ALLOWED_PARENT_CLASSIFICATIONS = "CX.allowedParentCl";

    /** Should items behind an object reparent */
    public static final String REPARENT_HIDDEN = "CX.reparentHidden";

    //---------------------------------------------------------------
    // Scale Change Model Rules rules
    //---------------------------------------------------------------

    /**
     * Determines whether or not the rule evaluates
     */
    public static final String SCALE_CHANGE_MODEL_FLAG = "CX.SKUX3DChange";

    /**
     * The axis the scale should occur on?
     */
    public static final String SCALE_CHANGE_MODEL_AXIS = "CX.SKUX3DAxis";

    /**
     * Size values to change models at ( actual sizes not increments)
     */
    public static final String SCALE_CHANGE_MODEL_SIZE = "CX.SKUX3DSize";
    /**
     *  alternate products to load up matched to size index
     */
    public static final String SCALE_CHANGE_MODEL_PROD = "CX.SKUX3DProd";


    //---------------------------------------------------------------
    // OverHang Rule Properties
    //---------------------------------------------------------------

    /**
     *  A boolean that determines if the rule should be evalutated if true
     */
    public static final String RESPECTS_OVERHANG_LIMIT = "CX.useOverLmt";

    /**
     * A float that determines the minimum overhang, default is 0
     */
    public static final String OVERHANG_MINIMUM = "CX.overhangMin";

    /**
     * A float that determines the maximum overhang, default is Float.MAX_VALUE
     */
    public static final String OVERHANG_LIMIT = "CX.overhangLmt";

    //---------------------------------------------------------------
    // Catalog visibility
    //---------------------------------------------------------------

    /** Hides products with models in the catalog so the user can't add them */
    public static final String HIDE_IN_CATALOG = "CX.hideInCatalog";


    //---------------------------------------------------------------
    // Order Report visibility
    //---------------------------------------------------------------

    /** Hides products with models in the catalog so the user can't add them */
    public static final String SHOW_IN_REPORT = "CX.showOnReport";


    //---------------------------------------------------------------
    // Dynamic orientation
    //---------------------------------------------------------------

    /** Flags a product as having orientation requirements to evaluate */
    public static final String HAS_ORIENTATION_REQUIREMENTS = "CX.orienReq";

    /** Flags existing orientation as left or right */
    public static final String CURRENT_ORIENTATION = "CX.curOrient";

    /** Ordered list of swap id's for orientation cases */
    public static final String ORIENTATION_SWAP_ID_LIST = "CX.orienSwapLs";

    //---------------------------------------------------------------
    // Products with Zones and Product Zones
    //---------------------------------------------------------------

    /** The number of product zones specified */
    public static final String PRODUCT_ZONE_COUNT = "CX.prodZoneCnt";

    /** Product zone x axis position list */
    public static final String PRODUCT_ZONE_POINT_X = "CX.prodZonePTX";

    /** Product zone y axis position list */
    public static final String PRODUCT_ZONE_POINT_Y = "CX.prodZonePTY";

    /** Product zone z axis position list */
    public static final String PRODUCT_ZONE_POINT_Z = "CX.prodZonePTZ";

    /** Product zone width list */
    public static final String PRODUCT_ZONE_WIDTH = "CX.prodZoneWidth";

    /** Product zone height list */
    public static final String PRODUCT_ZONE_HEIGHT = "CX.prodZoneHeight";

    /** Product zone normal x axis list */
    public static final String PRODUCT_ZONE_NORMAL_X = "CX.prodZoneNormX";

    /** Product zone normal y axis list */
    public static final String PRODUCT_ZONE_NORMAL_Y = "CX.prodZoneNormY";

    /** Product zone normal z axis list */
    public static final String PRODUCT_ZONE_NORMAL_Z = "CX.prodZoneNormZ";

    /** Product zone name list */
    public static final String PRODUCT_ZONE_NAMES = "CX.prodZoneNames";

    /** Product zone classification list */
    public static final String PRODUCT_ZONE_CLASSIFICATIONS = "CX.prodZoneClass";

    //---------------------------------------------------------------
    // Edge Face Snapping
    //---------------------------------------------------------------
    /** Turn on edge face snapping */
    public static final String USE_EDGE_FACE_SNAP = "CX.edgeFaceSnap";

    /** Target classifications */
    public static final String EDGE_FACE_TARGET_CLASSIFICATIONS = "CX.edgeSnapClass";

    /** Turn on edge vector */
    public static final String EDGE_FACE_USE_EDGE_VECTOR = "CX.useEdgeVector";

    /** The edge vector to use */
    public static final String EDGE_FACE_EDGE_VECTOR = "CX.edgeVector";

    /** Limit orientation to Axis */
    public static final String EDGE_FACE_ORIENTATION_LIMIT = "CX.edgeOrientLmt";

    //---------------------------------------------------------------
    // Product Packaging
    //---------------------------------------------------------------

    /** Is this product a package */
    public static final String PACK_IS_PACKAGE = "CX.isPackage";

    /** Package Skus */
    public static final String PACK_SKUS = "CX.packageSKUs";

    /** Package Quantities */
    public static final String PACK_QUANTITIES = "CX.packageQuantities";

    /** Is part of a package */
    public static final String PACK_IS_PACKAGE_PART = "CX.isPackagePart";

    /** Package SKU */
    public static final String PACK_SKU = "CX.packageSKU";

    //---------------------------------------------------------------
    // Mitre Cuts
    //---------------------------------------------------------------
    /** Can Mitre Cut */
    public static final String MITRE_CAN_CUT = "CX.canMitreCut";
    
    /** Translation applied to the shape relative to the spine */
    public static final String MITRE_SHAPE_TRANSLATION = "CX.mitreShapeTrans";

    /** Has Mitre cut lines */
    public static final String MITRE_HAS_LINE = "CX.hasMitreLine";

    /** Has Mitre cut lines */
    public static final String MITRE_USE_LINE = "CX.useMitreLine";

    /** Auto generated mitre or custom */
    public static final String MITRE_AUTO = "CX.autoMitreLine";

    /** Custom Mitre Line Back Left */
    public static final String MITRE_LINE_BACK_LEFT = "CX.mitreLineBackLeft";

    /** Custom Mitre Line Front Left */
    public static final String MITRE_LINE_FRONT_LEFT = "CX.mitreLineFrontLeft";

    /** Custom Mitre Line Front Right */
    public static final String MITRE_LINE_FRONT_RIGHT = "CX.mitreLineFrontRight";

    /** Custom Mitre Line Front Right */
    public static final String MITRE_LINE_BACK_RIGHT = "CX.mitreLineBackRight";

    //---------------------------------------------------------------
    // Complex Products
    //---------------------------------------------------------------

    /** The default combination string */
    public static final String COMPLEX_PRODUCT_DEFAULT_SELECTION = "CX.cpxDefault";

    /** The complete list of combination strings */
    public static final String COMPLEX_PRODUCT_COMBINATIONS = "CX.cpxCombinations";

    /** The complete list of tools ids */
    public static final String COMPLEX_PRODUCT_TOOL_IDS = "CX.cpxToolIds";

    /** The complete list of positions */
    public static final String COMPLEX_PRODUCT_POSITIONS = "CX.cpxPositions";

    /** The complete list of rotations */
    public static final String COMPLEX_PRODUCT_ROTATIONS = "CX.cpxRotations";

    /** The complete list of options */
    public static final String COMPLEX_PRODUCT_OPTIONS = "CX.cpxOptions";

    //---------------------------------------------------------------
    // Order Report
    //---------------------------------------------------------------
    
    /** The product's report abbreviation. string, default is null */
    public static final String REPORT_ABBREVIATION = "CX.abbr";
    
    /** The product's show cut length flag.  boolean, default is false. */
    public static final String REPORT_SHOW_CUT_LENGTH = "CX.showCutLen";
    
    //---------------------------------------------------------------
    // Sectional Products
    //---------------------------------------------------------------

    /** The product's sectional flag, used to flag an item for use by a
     * vertical section visual component.  boolean, default is false. */
    public static final String IS_SECTIONAL_PRODUCT = "CX.isSectional";  
    
    //---------------------------------------------------------------
    //---------------------------------------------------------------
    // Class methods
    //---------------------------------------------------------------
    //---------------------------------------------------------------

    //---------------------------------------------------------------
    // public methods
    //---------------------------------------------------------------

    /**
     * Get the default value associated with the rule property.
     *
     * @param property Property name
     * @return Object default value
     */
    public static Object getDefaultValue(String property) {

        return defaultValueMap.get(property);
    }

    //---------------------------------------------------------------
    // private methods
    //---------------------------------------------------------------

    /**
     * Initialize default value map
     */
    private void initializeDefaultValues() {

        defaultValueMap = new HashMap<String, Object>();

        defaultValueMap.put(SHADOW_ENTITY_FLAG, new Boolean(false));
        defaultValueMap.put(PROXIMITY_SNAP_TARGET_ENTITY_ID, new Integer(-1));

        defaultValueMap.put(PROXIMITY_HEIGHT_SNAP_ABOVE_BELOW,
                PROX_SNAP_HEIGHT_OPT.EVEN);

        defaultValueMap.put(IS_EDITABLE_PROP, new Boolean(true));
        defaultValueMap.put(MAXIMUM_OBJECT_SIZE_PROP, null);
        defaultValueMap.put(MINIMUM_OBJECT_SIZE_PROP, null);
        defaultValueMap.put(EMBED_DEPTH_PROP, null);
        defaultValueMap.put(CAN_DELETE_PROP, new Boolean(true));
        defaultValueMap.put(IGNORE_WALL_EDGE_SNAP, new Boolean(true));
        defaultValueMap.put(GLOBAL_DEPTH_OFFSET, null);
        defaultValueMap.put(SINGLE_SELECTION_ONLY, new Boolean(false));

        defaultValueMap.put(MOVEMENT_RESTRICTED_TO_BOUNDARY, new Boolean(true));
        defaultValueMap.put(MOVEMENT_RESTRICTED_TO_PLANE_PROP,
                MOVEMENT_PLANE_RESTRICTION_VALUES.NONE);
        defaultValueMap.put(MOVEMENT_USES_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(MOVEMENT_USES_ABSOLUTE_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(MOVEMENT_ABSOLUTE_X_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_ABSOLUTE_Y_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_ABSOLUTE_Z_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_USES_INCREMENTAL_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(MOVEMENT_INCREMENTAL_X_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_INCREMENTAL_Y_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_INCREMENTAL_Z_AXIS_SNAP_PROP, null);
        defaultValueMap.put(MOVEMENT_INCREMENTAL_X_AXIS_SPECIAL_CASE_SNAP_PROP,
                null);
        defaultValueMap.put(MOVEMENT_INCREMENTAL_Y_AXIS_SPECIAL_CASE_SNAP_PROP,
                null);
        defaultValueMap.put(MOVEMENT_INCREMENTAL_Z_AXIS_SPECIAL_CASE_SNAP_PROP,
                null);

        defaultValueMap.put(SNAP_RELATIVE_TO_FLOOR, new Boolean(false));
        defaultValueMap.put(SNAP_TO_CLASS_PROP, null);
        defaultValueMap.put(PROX_SNAP_STYLE_PROP, PROX_SNAP_STYLE.CENTER);
        defaultValueMap.put(INC_X_SNAP_EXCLUDE_PROP, null);
        defaultValueMap.put(INC_Y_SNAP_EXCLUDE_PROP, null);
        defaultValueMap.put(INC_Z_SNAP_EXCLUDE_PROP, null);
        defaultValueMap.put(MOVEMENT_IGNORE_RESTRICT_ON_ADD, new Boolean(false));
        defaultValueMap.put(SNAP_TO_MODEL_ONLY, new Boolean(false));
        defaultValueMap.put(FREE_FLOATING_CONSTRAINED, new Boolean(false));
        defaultValueMap.put(USES_STICKY_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(STICKY_SNAP_INDEX_PROP, null);
        defaultValueMap.put(MOVEMENT_SCALES_ATTACHED_PRODUCTS, new Boolean(false));

        defaultValueMap.put(CAN_ROTATE_PROP, new Boolean(false));
        defaultValueMap.put(ROTATION_AXIS_RESTRICTION_PROP,
                TARGET_ADJUSTMENT_AXIS.NONE);
        defaultValueMap.put(SNAP_TO_ROTATION_INCREMENT_PROP, null);
        defaultValueMap.put(SNAP_TO_ROTATION_ABSOLUTE_VALUE_PROP, null);

        defaultValueMap.put(CAN_SCALE_PROP, new Boolean(true));
        defaultValueMap.put(SCALE_RESTRICTION_PROP,
                SCALE_RESTRICTION_VALUES.NONE);
        defaultValueMap.put(SCALE_BOUND_BY_PARENT_PROP, null);
        defaultValueMap.put(SCALE_USES_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(SCALE_USES_ABSOLUTE_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(SCALE_ABSOLUTE_X_AXIS_SNAP_PROP, null);
        defaultValueMap.put(SCALE_ABSOLUTE_Y_AXIS_SNAP_PROP, null);
        defaultValueMap.put(SCALE_ABSOLUTE_Z_AXIS_SNAP_PROP, null);
        defaultValueMap.put(SCALE_USES_INCREMENTAL_SNAPS_PROP, new Boolean(false));
        defaultValueMap.put(SCALE_INCREMENTAL_X_AXIS_SNAP_PROP, null);
        defaultValueMap.put(SCALE_INCREMENTAL_Y_AXIS_SNAP_PROP, null);
        defaultValueMap.put(SCALE_INCREMENTAL_Z_AXIS_SNAP_PROP, null);

        defaultValueMap.put(CLASSIFICATION_PROP, null);
        defaultValueMap.put(RELATIONSHIP_CLASSIFICATION_PROP, null);
        defaultValueMap.put(RELATIONSHIP_AMOUNT_PROP, null);
        defaultValueMap.put(RELATIONSHIP_MODIFIER_PROP, null);
        defaultValueMap.put(GENERAL_POSITION_PROP, null);
        defaultValueMap.put(GENERAL_POSITION_DIRECTION_PROP, new Boolean(true));
        defaultValueMap.put(BOTTOM_POS_BUFFER_PROP, null);
        defaultValueMap.put(TOP_POS_BUFFER_PROP, new Float(0.0f));
        defaultValueMap.put(LEFT_POS_BUFFER_PROP, new Float(0.0f));
        defaultValueMap.put(RIGHT_POS_BUFFER_PROP, new Float(0.0f));
        defaultValueMap.put(FRONT_POS_BUFFER_PROP, new Float(0.0f));
        defaultValueMap.put(BACK_POS_BUFFER_PROP, new Float(0.0f));
        defaultValueMap.put(CENTER_HORIZONTAL_POS_BUFF_PROP, new Float(0.0f));
        defaultValueMap.put(CENTER_VERTICAL_POS_BUFF_PROP, new Float(0.0f));
        defaultValueMap.put(CENTER_DEPTH_POS_BUFF_PROP, new Float(0.0f));
        defaultValueMap.put(REPLACE_PROD_CLASS_PROP, null);
        defaultValueMap.put(PARENT_AS_COLLISION_ALTERNATE, new Boolean(false));
        defaultValueMap.put(USE_MULTI_COLLISION_POSITIONING, new Boolean(false));


        defaultValueMap.put(NO_MODEL_PROP, new Boolean(false));
        defaultValueMap.put(IGNORE_AUTO_ADD_DEL_RESTRICTION, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_BY_SPAN_USE, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_BY_SPAN_PROP, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_AXIS, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_STEP_SIZE, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_FORCE_FIT, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_NEG_OFFSET, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_POS_OFFSET, null);
        defaultValueMap.put(AUTO_ADD_BY_SPAN_MIN_REQUIRED, new Integer(0));
        defaultValueMap.put(AUTO_ADD_BY_SPAN_CONDITIONS, AUTO_ADD_CONDITION.FIRST_OR_FAIL);
        defaultValueMap.put(AUTO_ADD_BY_COL_USE, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_COL_PROP, null);
        defaultValueMap.put(AUTO_ADD_COL_AXIS, null);
        defaultValueMap.put(AUTO_ADD_COL_STEP_SIZE, null);
        defaultValueMap.put(AUTO_ADD_COL_FORCE_FIT, null);
        defaultValueMap.put(AUTO_ADD_COL_MIN_REQUIRED, new Integer(0));
        defaultValueMap.put(AUTO_ADD_COL_CONDITIONS, AUTO_ADD_CONDITION.FIRST_OR_FAIL);
        defaultValueMap.put(AUTO_ADD_N_UNITS_PROP_USE, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_N_PROP, null);
        defaultValueMap.put(AUTO_ADD_N_X_POS_PROP, null);
        defaultValueMap.put(AUTO_ADD_N_Y_POS_PROP, null);
        defaultValueMap.put(AUTO_ADD_N_Z_POS_PROP, null);
        defaultValueMap.put(AUTO_ADD_N_MIN_REQUIRED, new Integer(0));
        defaultValueMap.put(AUTO_ADD_N_CONDITIONS, AUTO_ADD_CONDITION.FIRST_OR_FAIL);
        defaultValueMap.put(AUTO_ADD_ENDS_PLACEMENT_USE, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_ENDS_PROP, null);
        defaultValueMap.put(AUTO_ADD_ENDS_AXIS, null);
        defaultValueMap.put(AUTO_ADD_ENDS_POS_OFFSET, null);
        defaultValueMap.put(AUTO_ADD_ENDS_NEG_OFFSET, null);
        defaultValueMap.put(AUTO_ADD_ENDS_OPTION, END_OPTION.BOTH);
        defaultValueMap.put(AUTO_ADD_ENDS_CONDITIONS, AUTO_ADD_CONDITION.FIRST_OR_FAIL);
        defaultValueMap.put(AUTO_ADD_INVISIBLE_CHILDREN_USE, new Boolean(false));
        defaultValueMap.put(AUTO_ADD_INVISIBLE_CHILDREN_PROP, null);

        defaultValueMap.put(AUTO_ADD_END_ORIENTATION , ORIENTATION.DEFAULT);
        defaultValueMap.put(IS_AUTO_ADD_PRODUCT, new Boolean(false));
        defaultValueMap.put(PERMANENT_PARENT_SET, new Boolean(false));
        defaultValueMap.put(MITRE_CUT_SPINE_LAST_GOOD, new float[] {});
        defaultValueMap.put(MITRE_CUT_VISIBLE_LAST_GOOD, new boolean[] {});
        defaultValueMap.put(MITRE_CUT_ENABLE_LAST_GOOD, new boolean[] {});
        defaultValueMap.put(MOVEMENT_SCALE_ATTACHED_TARGETS, null);

        defaultValueMap.put(USE_NEAREST_NEIGHBOR_BOUNDS_ADJ, null);
        defaultValueMap.put(NEAREST_NEIGHBOR_X_AXIS_ADJ, null);
        defaultValueMap.put(NEAREST_NEIGHBOR_Y_AXIS_ADJ, null);
        defaultValueMap.put(NEAREST_NEIGHBOR_Z_AXIS_ADJ, null);

        defaultValueMap.put(MAXIMUM_HEIGHT_PLACE_PROP, new Float(0.0f));
        defaultValueMap.put(MINIMUM_HEIGHT_PLACE_PROP, new Float(0.0f));
        defaultValueMap.put(STACK_PROP, null);
        defaultValueMap.put(MUST_FIT_INSIDE_PARENT, new Boolean(false));
        defaultValueMap.put(PLACED_INSIDE_PARENT, new Boolean(false));

        defaultValueMap.put(PROD_SCALE_CHANGE_SKU_PROP, new Boolean(false));
        defaultValueMap.put(PROD_SCALE_SKU_STEP_X_PROP, null);
        defaultValueMap.put(PROD_SCALE_SKU_STEP_Y_PROP, null);
        defaultValueMap.put(PROD_SCALE_SKU_STEP_Z_PROP, null);
        defaultValueMap.put(PROD_SCALE_SIZE_STEP_X_PROP, null);
        defaultValueMap.put(PROD_SCALE_SIZE_STEP_Y_PROP, null);
        defaultValueMap.put(PROD_SCALE_SIZE_STEP_Z_PROP, null);
        defaultValueMap.put(PROD_SCALE_DESC_STEP_X_PROP, null);
        defaultValueMap.put(PROD_SCALE_DESC_STEP_Y_PROP, null);
        defaultValueMap.put(PROD_SCALE_DESC_STEP_Z_PROP, null);

        defaultValueMap.put(SPAN_OBJECT_PROP, new Boolean(false));
        defaultValueMap.put(SPAN_OBJECT_DEPTH_OFFSET_PROP, new Float(0.25f));
        defaultValueMap.put(CAN_PLACE_ON_SPAN_OBJECT, new Boolean(false));

        defaultValueMap.put(COLLISION_POSITION_REQUIREMENTS, new Boolean(false));
        defaultValueMap.put(COLLISION_POSITION_X_AXIS_VALUES, null);
        defaultValueMap.put(COLLISION_POSITION_Y_AXIS_VALUES, null);
        defaultValueMap.put(COLLISION_POSITION_Z_AXIS_VALUES, null);
        defaultValueMap.put(COLLISION_POSITION_TOLERANCE, null);
        defaultValueMap.put(COLLISION_TARGET_ADJUSTMENT_AXIS,
                TARGET_ADJUSTMENT_AXIS.XAXIS);

        defaultValueMap.put(USES_PERMANENT_PARENT, new Boolean(false));
        defaultValueMap.put(ALLOWED_PARENT_CLASSIFICATIONS, null);
        defaultValueMap.put(REPARENT_HIDDEN, new Boolean(false));

        defaultValueMap.put(SCALE_CHANGE_MODEL_FLAG, new Boolean(false));
        defaultValueMap.put(SCALE_CHANGE_MODEL_AXIS, SCALE_CHANGE_MODEL_AXIS_VALUES.XAXIS);
        defaultValueMap.put(SCALE_CHANGE_MODEL_SIZE, null);
        defaultValueMap.put(SCALE_CHANGE_MODEL_PROD, null);

        defaultValueMap.put(RESPECTS_OVERHANG_LIMIT, new Boolean(false));
        defaultValueMap.put(OVERHANG_MINIMUM, new Float(0f));
        defaultValueMap.put(OVERHANG_LIMIT, Float.MAX_VALUE);

        defaultValueMap.put(HIDE_IN_CATALOG, new Boolean(false));
        defaultValueMap.put(SHOW_IN_REPORT, new Boolean(true));

        defaultValueMap.put(HAS_ORIENTATION_REQUIREMENTS, new Boolean(false));
        defaultValueMap.put(CURRENT_ORIENTATION, ORIENTATION_STATE.RIGHT);
        defaultValueMap.put(ORIENTATION_SWAP_ID_LIST, null);

        defaultValueMap.put(COLLISION_ALIGNMENT_METHOD,
                COLLISION_ALIGNMENT_METHOD_VALUES.CENTER);
        defaultValueMap.put(COLLISION_POSITION_MULTI_ZONE_REQUIREMENTS, new Boolean(false));

        defaultValueMap.put(PRODUCT_ZONE_COUNT, new Integer(0));
        defaultValueMap.put(PRODUCT_ZONE_POINT_X, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_POINT_Y, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_POINT_Z, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_WIDTH, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_HEIGHT, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_NORMAL_X, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_NORMAL_Y, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_NORMAL_Z, new float[]{});
        defaultValueMap.put(PRODUCT_ZONE_NAMES, new String[]{});
        defaultValueMap.put(PRODUCT_ZONE_CLASSIFICATIONS, new String[]{});

        defaultValueMap.put(USE_EDGE_FACE_SNAP, new Boolean(false));
        defaultValueMap.put(EDGE_FACE_TARGET_CLASSIFICATIONS, new String[] {});
        defaultValueMap.put(EDGE_FACE_USE_EDGE_VECTOR, new Boolean(false));
        defaultValueMap.put(EDGE_FACE_EDGE_VECTOR, new float[] {});
        defaultValueMap.put(EDGE_FACE_ORIENTATION_LIMIT,
                EDGE_FACE_SNAP_ADJ_AXIS.XAXIS);

        defaultValueMap.put(PACK_IS_PACKAGE, new Boolean(false));
        defaultValueMap.put(PACK_SKUS, new String[] {});
        defaultValueMap.put(PACK_QUANTITIES, new int[] {});
        defaultValueMap.put(PACK_IS_PACKAGE_PART, new Boolean(false));
        defaultValueMap.put(PACK_SKU, new String[] {});

        defaultValueMap.put(MITRE_CAN_CUT, new Boolean(false));
        defaultValueMap.put(MITRE_SHAPE_TRANSLATION, new float[] {0.0f, 0.0f, 0.0f});
        defaultValueMap.put(MITRE_HAS_LINE, new Boolean(false));
        defaultValueMap.put(MITRE_USE_LINE, new Boolean(false));
        defaultValueMap.put(MITRE_AUTO, new Boolean(false));
        defaultValueMap.put(MITRE_LINE_BACK_LEFT, new float[] {});
        defaultValueMap.put(MITRE_LINE_FRONT_LEFT, new float[] {});
        defaultValueMap.put(MITRE_LINE_FRONT_RIGHT, new float[] {});
        defaultValueMap.put(MITRE_LINE_BACK_RIGHT, new float[] {});

        defaultValueMap.put(IS_COMPLEX_PRODUCT, new Boolean(false));
        defaultValueMap.put(IS_COMPLEX_PRODUCT_SUBPART, new Boolean(false));
        
        defaultValueMap.put(REPORT_ABBREVIATION, null);
        defaultValueMap.put(REPORT_SHOW_CUT_LENGTH, new Boolean(false));
        
        defaultValueMap.put(IS_SECTIONAL_PRODUCT, new Boolean(false));

    }
}
