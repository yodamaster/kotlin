/*
 * Generated file
 * DO NOT EDIT
 * 
 * See libraries/tools/idl2k for details
 */

package org.w3c.dom.svg

import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.css.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

@native public open class SVGDocument : Document() {
    open val rootElement: SVGSVGElement
        get() = noImpl
}

@native public abstract class SVGSVGElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGLocatable, SVGFitToViewBox, SVGZoomAndPan {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open var contentScriptType: dynamic
        get() = noImpl
        set(value) = noImpl
    open var contentStyleType: dynamic
        get() = noImpl
        set(value) = noImpl
    open val viewport: SVGRect
        get() = noImpl
    open val pixelUnitToMillimeterX: Float
        get() = noImpl
    open val pixelUnitToMillimeterY: Float
        get() = noImpl
    open val screenPixelToMillimeterX: Float
        get() = noImpl
    open val screenPixelToMillimeterY: Float
        get() = noImpl
    open val useCurrentView: Boolean
        get() = noImpl
    open val currentView: SVGViewSpec
        get() = noImpl
    open var currentScale: Float
        get() = noImpl
        set(value) = noImpl
    open val currentTranslate: SVGPoint
        get() = noImpl
    fun suspendRedraw(maxWaitMilliseconds: Int): Int = noImpl
    fun unsuspendRedraw(suspendHandleID: Int): Unit = noImpl
    fun unsuspendRedrawAll(): Unit = noImpl
    fun forceRedraw(): Unit = noImpl
    fun pauseAnimations(): Unit = noImpl
    fun unpauseAnimations(): Unit = noImpl
    fun animationsPaused(): Boolean = noImpl
    fun getCurrentTime(): Float = noImpl
    fun setCurrentTime(seconds: Float): Unit = noImpl
    fun getIntersectionList(rect: SVGRect, referenceElement: SVGElement): NodeList = noImpl
    fun getEnclosureList(rect: SVGRect, referenceElement: SVGElement): NodeList = noImpl
    fun checkIntersection(element: SVGElement, rect: SVGRect): Boolean = noImpl
    fun checkEnclosure(element: SVGElement, rect: SVGRect): Boolean = noImpl
    fun deselectAll(): Unit = noImpl
    fun createSVGNumber(): SVGNumber = noImpl
    fun createSVGLength(): SVGLength = noImpl
    fun createSVGAngle(): SVGAngle = noImpl
    fun createSVGPoint(): SVGPoint = noImpl
    fun createSVGMatrix(): SVGMatrix = noImpl
    fun createSVGRect(): SVGRect = noImpl
    fun createSVGTransform(): SVGTransform = noImpl
    fun createSVGTransformFromMatrix(matrix: SVGMatrix): SVGTransform = noImpl
    fun getElementById(elementId: String): Element = noImpl
}

@native public abstract class SVGGElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
}

@native public abstract class SVGDefsElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
}

@native public abstract class SVGDescElement : SVGElement, SVGLangSpace, SVGStylable {
}

@native public abstract class SVGTitleElement : SVGElement, SVGLangSpace, SVGStylable {
}

@native public abstract class SVGSymbolElement : SVGElement, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGFitToViewBox {
}

@native public abstract class SVGUseElement : SVGElement, SVGURIReference, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open val instanceRoot: SVGElementInstance
        get() = noImpl
    open val animatedInstanceRoot: SVGElementInstance
        get() = noImpl
}

@native public abstract class SVGElementInstance : EventTarget() {
    open val correspondingElement: SVGElement
        get() = noImpl
    open val correspondingUseElement: SVGUseElement
        get() = noImpl
    open val parentNode: SVGElementInstance
        get() = noImpl
    open val childNodes: SVGElementInstanceList
        get() = noImpl
    open val firstChild: SVGElementInstance
        get() = noImpl
    open val lastChild: SVGElementInstance
        get() = noImpl
    open val previousSibling: SVGElementInstance
        get() = noImpl
    open val nextSibling: SVGElementInstance
        get() = noImpl
}

@native public abstract class SVGElementInstanceList {
    open val length: Int
        get() = noImpl
    fun item(index: Int): SVGElementInstance = noImpl
}

@native public abstract class SVGImageElement : SVGElement, SVGURIReference, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open val preserveAspectRatio: SVGAnimatedPreserveAspectRatio
        get() = noImpl
}

@native public abstract class SVGSwitchElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
}

@native public abstract class GetSVGDocument {
    fun getSVGDocument(): SVGDocument = noImpl
}

@native public abstract class SVGElement : Element() {
//    open var id: dynamic
//        get() = noImpl
//        set(value) = noImpl
    open var xmlbase: dynamic
        get() = noImpl
        set(value) = noImpl
    open val ownerSVGElement: SVGSVGElement
        get() = noImpl
    open val viewportElement: SVGElement
        get() = noImpl
    open val style: CSSStyleDeclaration
        get() = noImpl
}

@native public abstract class SVGAnimatedBoolean {
    open var baseVal: dynamic
        get() = noImpl
        set(value) = noImpl
    open val animVal: Boolean
        get() = noImpl
}

@native public abstract class SVGAnimatedString {
    open var baseVal: dynamic
        get() = noImpl
        set(value) = noImpl
    open val animVal: String
        get() = noImpl
}

@native public abstract class SVGStringList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: String): String = noImpl
    fun getItem(index: Int): String = noImpl
    fun insertItemBefore(newItem: String, index: Int): String = noImpl
    fun replaceItem(newItem: String, index: Int): String = noImpl
    fun removeItem(index: Int): String = noImpl
    fun appendItem(newItem: String): String = noImpl
}

@native public abstract class SVGAnimatedEnumeration {
    open var baseVal: dynamic
        get() = noImpl
        set(value) = noImpl
    open val animVal: Short
        get() = noImpl
}

@native public abstract class SVGAnimatedInteger {
    open var baseVal: dynamic
        get() = noImpl
        set(value) = noImpl
    open val animVal: Int
        get() = noImpl
}

@native public abstract class SVGNumber {
    open var value: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGAnimatedNumber {
    open var baseVal: dynamic
        get() = noImpl
        set(value) = noImpl
    open val animVal: Float
        get() = noImpl
}

@native public abstract class SVGNumberList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: SVGNumber): SVGNumber = noImpl
    fun getItem(index: Int): SVGNumber = noImpl
    fun insertItemBefore(newItem: SVGNumber, index: Int): SVGNumber = noImpl
    fun replaceItem(newItem: SVGNumber, index: Int): SVGNumber = noImpl
    fun removeItem(index: Int): SVGNumber = noImpl
    fun appendItem(newItem: SVGNumber): SVGNumber = noImpl
}

@native public abstract class SVGAnimatedNumberList {
    open val baseVal: SVGNumberList
        get() = noImpl
    open val animVal: SVGNumberList
        get() = noImpl
}

@native public abstract class SVGLength {
    open val unitType: Short
        get() = noImpl
    open var value: dynamic
        get() = noImpl
        set(value) = noImpl
    open var valueInSpecifiedUnits: dynamic
        get() = noImpl
        set(value) = noImpl
    open var valueAsString: dynamic
        get() = noImpl
        set(value) = noImpl
    fun newValueSpecifiedUnits(unitType: Short, valueInSpecifiedUnits: Float): Unit = noImpl
    fun convertToSpecifiedUnits(unitType: Short): Unit = noImpl

    companion object {
        val SVG_LENGTHTYPE_UNKNOWN: Short = 0
        val SVG_LENGTHTYPE_NUMBER: Short = 1
        val SVG_LENGTHTYPE_PERCENTAGE: Short = 2
        val SVG_LENGTHTYPE_EMS: Short = 3
        val SVG_LENGTHTYPE_EXS: Short = 4
        val SVG_LENGTHTYPE_PX: Short = 5
        val SVG_LENGTHTYPE_CM: Short = 6
        val SVG_LENGTHTYPE_MM: Short = 7
        val SVG_LENGTHTYPE_IN: Short = 8
        val SVG_LENGTHTYPE_PT: Short = 9
        val SVG_LENGTHTYPE_PC: Short = 10
    }
}

@native public abstract class SVGAnimatedLength {
    open val baseVal: SVGLength
        get() = noImpl
    open val animVal: SVGLength
        get() = noImpl
}

@native public abstract class SVGLengthList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: SVGLength): SVGLength = noImpl
    fun getItem(index: Int): SVGLength = noImpl
    fun insertItemBefore(newItem: SVGLength, index: Int): SVGLength = noImpl
    fun replaceItem(newItem: SVGLength, index: Int): SVGLength = noImpl
    fun removeItem(index: Int): SVGLength = noImpl
    fun appendItem(newItem: SVGLength): SVGLength = noImpl
}

@native public abstract class SVGAnimatedLengthList {
    open val baseVal: SVGLengthList
        get() = noImpl
    open val animVal: SVGLengthList
        get() = noImpl
}

@native public abstract class SVGAngle {
    open val unitType: Short
        get() = noImpl
    open var value: dynamic
        get() = noImpl
        set(value) = noImpl
    open var valueInSpecifiedUnits: dynamic
        get() = noImpl
        set(value) = noImpl
    open var valueAsString: dynamic
        get() = noImpl
        set(value) = noImpl
    fun newValueSpecifiedUnits(unitType: Short, valueInSpecifiedUnits: Float): Unit = noImpl
    fun convertToSpecifiedUnits(unitType: Short): Unit = noImpl

    companion object {
        val SVG_ANGLETYPE_UNKNOWN: Short = 0
        val SVG_ANGLETYPE_UNSPECIFIED: Short = 1
        val SVG_ANGLETYPE_DEG: Short = 2
        val SVG_ANGLETYPE_RAD: Short = 3
        val SVG_ANGLETYPE_GRAD: Short = 4
    }
}

@native public abstract class SVGAnimatedAngle {
    open val baseVal: SVGAngle
        get() = noImpl
    open val animVal: SVGAngle
        get() = noImpl
}

@native public abstract class SVGColor {
    open val colorType: Short
        get() = noImpl
    open val rgbColor: dynamic
        get() = noImpl
    open val iccColor: SVGICCColor
        get() = noImpl
    fun setRGBColor(rgbColor: String): Unit = noImpl
    fun setRGBColorICCColor(rgbColor: String, iccColor: String): Unit = noImpl
    fun setColor(colorType: Short, rgbColor: String, iccColor: String): Unit = noImpl

    companion object {
        val SVG_COLORTYPE_UNKNOWN: Short = 0
        val SVG_COLORTYPE_RGBCOLOR: Short = 1
        val SVG_COLORTYPE_RGBCOLOR_ICCCOLOR: Short = 2
        val SVG_COLORTYPE_CURRENTCOLOR: Short = 3
    }
}

@native public abstract class SVGICCColor {
    open var colorProfile: dynamic
        get() = noImpl
        set(value) = noImpl
    open val colors: SVGNumberList
        get() = noImpl
}

@native public abstract class SVGRect {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var width: dynamic
        get() = noImpl
        set(value) = noImpl
    open var height: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGAnimatedRect {
    open val baseVal: SVGRect
        get() = noImpl
    open val animVal: SVGRect
        get() = noImpl
}

@native public abstract class SVGUnitTypes {

    companion object {
        val SVG_UNIT_TYPE_UNKNOWN: Short = 0
        val SVG_UNIT_TYPE_USERSPACEONUSE: Short = 1
        val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: Short = 2
    }
}

@native public abstract class SVGStylable {
    fun getPresentationAttribute(name: String): dynamic = noImpl
}

@native public abstract class SVGLocatable {
    open val nearestViewportElement: SVGElement
        get() = noImpl
    open val farthestViewportElement: SVGElement
        get() = noImpl
    fun getBBox(): SVGRect = noImpl
    fun getCTM(): SVGMatrix = noImpl
    fun getScreenCTM(): SVGMatrix = noImpl
    fun getTransformToElement(element: SVGElement): SVGMatrix = noImpl
}

@native public abstract class SVGTransformable : SVGLocatable() {
    open val transform: SVGAnimatedTransformList
        get() = noImpl
}

@native public abstract class SVGTests {
    open val requiredFeatures: SVGStringList
        get() = noImpl
    open val requiredExtensions: SVGStringList
        get() = noImpl
    open val systemLanguage: SVGStringList
        get() = noImpl
    fun hasExtension(extension: String): Boolean = noImpl
}

@native public abstract class SVGLangSpace {
    open var xmllang: dynamic
        get() = noImpl
        set(value) = noImpl
    open var xmlspace: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGExternalResourcesRequired {
    open val externalResourcesRequired: SVGAnimatedBoolean
        get() = noImpl
}

@native public abstract class SVGFitToViewBox {
    open val viewBox: SVGAnimatedRect
        get() = noImpl
    open val preserveAspectRatio: SVGAnimatedPreserveAspectRatio
        get() = noImpl
}

@native public abstract class SVGZoomAndPan {
    open var zoomAndPan: dynamic
        get() = noImpl
        set(value) = noImpl

    companion object {
        val SVG_ZOOMANDPAN_UNKNOWN: Short = 0
        val SVG_ZOOMANDPAN_DISABLE: Short = 1
        val SVG_ZOOMANDPAN_MAGNIFY: Short = 2
    }
}

@native public abstract class SVGViewSpec : SVGZoomAndPan, SVGFitToViewBox {
    open val transform: SVGTransformList
        get() = noImpl
    open val viewTarget: SVGElement
        get() = noImpl
    open val viewBoxString: String
        get() = noImpl
    open val preserveAspectRatioString: String
        get() = noImpl
    open val transformString: String
        get() = noImpl
    open val viewTargetString: String
        get() = noImpl
}

@native public abstract class SVGURIReference {
    open val href: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGCSSRule : CSSRule() {

    companion object {
        val COLOR_PROFILE_RULE: Short = 7
    }
}

@native public abstract class SVGRenderingIntent {

    companion object {
        val RENDERING_INTENT_UNKNOWN: Short = 0
        val RENDERING_INTENT_AUTO: Short = 1
        val RENDERING_INTENT_PERCEPTUAL: Short = 2
        val RENDERING_INTENT_RELATIVE_COLORIMETRIC: Short = 3
        val RENDERING_INTENT_SATURATION: Short = 4
        val RENDERING_INTENT_ABSOLUTE_COLORIMETRIC: Short = 5
    }
}

@native public abstract class SVGStyleElement : SVGElement, SVGLangSpace {
    open var type: dynamic
        get() = noImpl
        set(value) = noImpl
    open var media: dynamic
        get() = noImpl
        set(value) = noImpl
    open var title: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPoint {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    fun matrixTransform(matrix: SVGMatrix): SVGPoint = noImpl
}

@native public abstract class SVGPointList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: SVGPoint): SVGPoint = noImpl
    fun getItem(index: Int): SVGPoint = noImpl
    fun insertItemBefore(newItem: SVGPoint, index: Int): SVGPoint = noImpl
    fun replaceItem(newItem: SVGPoint, index: Int): SVGPoint = noImpl
    fun removeItem(index: Int): SVGPoint = noImpl
    fun appendItem(newItem: SVGPoint): SVGPoint = noImpl
}

@native public abstract class SVGMatrix {
    open var a: dynamic
        get() = noImpl
        set(value) = noImpl
    open var b: dynamic
        get() = noImpl
        set(value) = noImpl
    open var c: dynamic
        get() = noImpl
        set(value) = noImpl
    open var d: dynamic
        get() = noImpl
        set(value) = noImpl
    open var e: dynamic
        get() = noImpl
        set(value) = noImpl
    open var f: dynamic
        get() = noImpl
        set(value) = noImpl
    fun multiply(secondMatrix: SVGMatrix): SVGMatrix = noImpl
    fun inverse(): SVGMatrix = noImpl
    fun translate(x: Float, y: Float): SVGMatrix = noImpl
    fun scale(scaleFactor: Float): SVGMatrix = noImpl
    fun scaleNonUniform(scaleFactorX: Float, scaleFactorY: Float): SVGMatrix = noImpl
    fun rotate(angle: Float): SVGMatrix = noImpl
    fun rotateFromVector(x: Float, y: Float): SVGMatrix = noImpl
    fun flipX(): SVGMatrix = noImpl
    fun flipY(): SVGMatrix = noImpl
    fun skewX(angle: Float): SVGMatrix = noImpl
    fun skewY(angle: Float): SVGMatrix = noImpl
}

@native public abstract class SVGTransform {
    open val type: Short
        get() = noImpl
    open val matrix: SVGMatrix
        get() = noImpl
    open val angle: Float
        get() = noImpl
    fun setMatrix(matrix: SVGMatrix): Unit = noImpl
    fun setTranslate(tx: Float, ty: Float): Unit = noImpl
    fun setScale(sx: Float, sy: Float): Unit = noImpl
    fun setRotate(angle: Float, cx: Float, cy: Float): Unit = noImpl
    fun setSkewX(angle: Float): Unit = noImpl
    fun setSkewY(angle: Float): Unit = noImpl

    companion object {
        val SVG_TRANSFORM_UNKNOWN: Short = 0
        val SVG_TRANSFORM_MATRIX: Short = 1
        val SVG_TRANSFORM_TRANSLATE: Short = 2
        val SVG_TRANSFORM_SCALE: Short = 3
        val SVG_TRANSFORM_ROTATE: Short = 4
        val SVG_TRANSFORM_SKEWX: Short = 5
        val SVG_TRANSFORM_SKEWY: Short = 6
    }
}

@native public abstract class SVGTransformList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: SVGTransform): SVGTransform = noImpl
    fun getItem(index: Int): SVGTransform = noImpl
    fun insertItemBefore(newItem: SVGTransform, index: Int): SVGTransform = noImpl
    fun replaceItem(newItem: SVGTransform, index: Int): SVGTransform = noImpl
    fun removeItem(index: Int): SVGTransform = noImpl
    fun appendItem(newItem: SVGTransform): SVGTransform = noImpl
    fun createSVGTransformFromMatrix(matrix: SVGMatrix): SVGTransform = noImpl
    fun consolidate(): SVGTransform = noImpl
}

@native public abstract class SVGAnimatedTransformList {
    open val baseVal: SVGTransformList
        get() = noImpl
    open val animVal: SVGTransformList
        get() = noImpl
}

@native public abstract class SVGPreserveAspectRatio {
    open var align: dynamic
        get() = noImpl
        set(value) = noImpl
    open var meetOrSlice: dynamic
        get() = noImpl
        set(value) = noImpl

    companion object {
        val SVG_PRESERVEASPECTRATIO_UNKNOWN: Short = 0
        val SVG_PRESERVEASPECTRATIO_NONE: Short = 1
        val SVG_PRESERVEASPECTRATIO_XMINYMIN: Short = 2
        val SVG_PRESERVEASPECTRATIO_XMIDYMIN: Short = 3
        val SVG_PRESERVEASPECTRATIO_XMAXYMIN: Short = 4
        val SVG_PRESERVEASPECTRATIO_XMINYMID: Short = 5
        val SVG_PRESERVEASPECTRATIO_XMIDYMID: Short = 6
        val SVG_PRESERVEASPECTRATIO_XMAXYMID: Short = 7
        val SVG_PRESERVEASPECTRATIO_XMINYMAX: Short = 8
        val SVG_PRESERVEASPECTRATIO_XMIDYMAX: Short = 9
        val SVG_PRESERVEASPECTRATIO_XMAXYMAX: Short = 10
        val SVG_MEETORSLICE_UNKNOWN: Short = 0
        val SVG_MEETORSLICE_MEET: Short = 1
        val SVG_MEETORSLICE_SLICE: Short = 2
    }
}

@native public abstract class SVGAnimatedPreserveAspectRatio {
    open val baseVal: SVGPreserveAspectRatio
        get() = noImpl
    open val animVal: SVGPreserveAspectRatio
        get() = noImpl
}

@native public abstract class SVGPathSeg {
    open val pathSegType: Short
        get() = noImpl
    open val pathSegTypeAsLetter: String
        get() = noImpl

    companion object {
        val PATHSEG_UNKNOWN: Short = 0
        val PATHSEG_CLOSEPATH: Short = 1
        val PATHSEG_MOVETO_ABS: Short = 2
        val PATHSEG_MOVETO_REL: Short = 3
        val PATHSEG_LINETO_ABS: Short = 4
        val PATHSEG_LINETO_REL: Short = 5
        val PATHSEG_CURVETO_CUBIC_ABS: Short = 6
        val PATHSEG_CURVETO_CUBIC_REL: Short = 7
        val PATHSEG_CURVETO_QUADRATIC_ABS: Short = 8
        val PATHSEG_CURVETO_QUADRATIC_REL: Short = 9
        val PATHSEG_ARC_ABS: Short = 10
        val PATHSEG_ARC_REL: Short = 11
        val PATHSEG_LINETO_HORIZONTAL_ABS: Short = 12
        val PATHSEG_LINETO_HORIZONTAL_REL: Short = 13
        val PATHSEG_LINETO_VERTICAL_ABS: Short = 14
        val PATHSEG_LINETO_VERTICAL_REL: Short = 15
        val PATHSEG_CURVETO_CUBIC_SMOOTH_ABS: Short = 16
        val PATHSEG_CURVETO_CUBIC_SMOOTH_REL: Short = 17
        val PATHSEG_CURVETO_QUADRATIC_SMOOTH_ABS: Short = 18
        val PATHSEG_CURVETO_QUADRATIC_SMOOTH_REL: Short = 19
    }
}

@native public abstract class SVGPathSegClosePath : SVGPathSeg() {
}

@native public abstract class SVGPathSegMovetoAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegMovetoRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoCubicAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y2: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoCubicRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y2: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoQuadraticAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y1: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoQuadraticRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y1: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegArcAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var r1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var r2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var angle: dynamic
        get() = noImpl
        set(value) = noImpl
    open var largeArcFlag: dynamic
        get() = noImpl
        set(value) = noImpl
    open var sweepFlag: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegArcRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var r1: dynamic
        get() = noImpl
        set(value) = noImpl
    open var r2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var angle: dynamic
        get() = noImpl
        set(value) = noImpl
    open var largeArcFlag: dynamic
        get() = noImpl
        set(value) = noImpl
    open var sweepFlag: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoHorizontalAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoHorizontalRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoVerticalAbs : SVGPathSeg() {
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegLinetoVerticalRel : SVGPathSeg() {
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoCubicSmoothAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y2: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoCubicSmoothRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x2: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y2: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoQuadraticSmoothAbs : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegCurvetoQuadraticSmoothRel : SVGPathSeg() {
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPathSegList {
    open val numberOfItems: Int
        get() = noImpl
    fun clear(): Unit = noImpl
    fun initialize(newItem: SVGPathSeg): SVGPathSeg = noImpl
    fun getItem(index: Int): SVGPathSeg = noImpl
    fun insertItemBefore(newItem: SVGPathSeg, index: Int): SVGPathSeg = noImpl
    fun replaceItem(newItem: SVGPathSeg, index: Int): SVGPathSeg = noImpl
    fun removeItem(index: Int): SVGPathSeg = noImpl
    fun appendItem(newItem: SVGPathSeg): SVGPathSeg = noImpl
}

@native public abstract class SVGAnimatedPathData {
    open val pathSegList: SVGPathSegList
        get() = noImpl
    open val normalizedPathSegList: SVGPathSegList
        get() = noImpl
    open val animatedPathSegList: SVGPathSegList
        get() = noImpl
    open val animatedNormalizedPathSegList: SVGPathSegList
        get() = noImpl
}

@native public abstract class SVGPathElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable, SVGAnimatedPathData {
    open val pathLength: SVGAnimatedNumber
        get() = noImpl
    fun getTotalLength(): Float = noImpl
    fun getPointAtLength(distance: Float): SVGPoint = noImpl
    fun getPathSegAtLength(distance: Float): Int = noImpl
    fun createSVGPathSegClosePath(): SVGPathSegClosePath = noImpl
    fun createSVGPathSegMovetoAbs(x: Float, y: Float): SVGPathSegMovetoAbs = noImpl
    fun createSVGPathSegMovetoRel(x: Float, y: Float): SVGPathSegMovetoRel = noImpl
    fun createSVGPathSegLinetoAbs(x: Float, y: Float): SVGPathSegLinetoAbs = noImpl
    fun createSVGPathSegLinetoRel(x: Float, y: Float): SVGPathSegLinetoRel = noImpl
    fun createSVGPathSegCurvetoCubicAbs(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float): SVGPathSegCurvetoCubicAbs = noImpl
    fun createSVGPathSegCurvetoCubicRel(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float): SVGPathSegCurvetoCubicRel = noImpl
    fun createSVGPathSegCurvetoQuadraticAbs(x: Float, y: Float, x1: Float, y1: Float): SVGPathSegCurvetoQuadraticAbs = noImpl
    fun createSVGPathSegCurvetoQuadraticRel(x: Float, y: Float, x1: Float, y1: Float): SVGPathSegCurvetoQuadraticRel = noImpl
    fun createSVGPathSegArcAbs(x: Float, y: Float, r1: Float, r2: Float, angle: Float, largeArcFlag: Boolean, sweepFlag: Boolean): SVGPathSegArcAbs = noImpl
    fun createSVGPathSegArcRel(x: Float, y: Float, r1: Float, r2: Float, angle: Float, largeArcFlag: Boolean, sweepFlag: Boolean): SVGPathSegArcRel = noImpl
    fun createSVGPathSegLinetoHorizontalAbs(x: Float): SVGPathSegLinetoHorizontalAbs = noImpl
    fun createSVGPathSegLinetoHorizontalRel(x: Float): SVGPathSegLinetoHorizontalRel = noImpl
    fun createSVGPathSegLinetoVerticalAbs(y: Float): SVGPathSegLinetoVerticalAbs = noImpl
    fun createSVGPathSegLinetoVerticalRel(y: Float): SVGPathSegLinetoVerticalRel = noImpl
    fun createSVGPathSegCurvetoCubicSmoothAbs(x: Float, y: Float, x2: Float, y2: Float): SVGPathSegCurvetoCubicSmoothAbs = noImpl
    fun createSVGPathSegCurvetoCubicSmoothRel(x: Float, y: Float, x2: Float, y2: Float): SVGPathSegCurvetoCubicSmoothRel = noImpl
    fun createSVGPathSegCurvetoQuadraticSmoothAbs(x: Float, y: Float): SVGPathSegCurvetoQuadraticSmoothAbs = noImpl
    fun createSVGPathSegCurvetoQuadraticSmoothRel(x: Float, y: Float): SVGPathSegCurvetoQuadraticSmoothRel = noImpl
}

@native public abstract class SVGRectElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open val rx: SVGAnimatedLength
        get() = noImpl
    open val ry: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGCircleElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val cx: SVGAnimatedLength
        get() = noImpl
    open val cy: SVGAnimatedLength
        get() = noImpl
    open val r: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGEllipseElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val cx: SVGAnimatedLength
        get() = noImpl
    open val cy: SVGAnimatedLength
        get() = noImpl
    open val rx: SVGAnimatedLength
        get() = noImpl
    open val ry: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGLineElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val x1: SVGAnimatedLength
        get() = noImpl
    open val y1: SVGAnimatedLength
        get() = noImpl
    open val x2: SVGAnimatedLength
        get() = noImpl
    open val y2: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGAnimatedPoints {
    open val points: SVGPointList
        get() = noImpl
    open val animatedPoints: SVGPointList
        get() = noImpl
}

@native public abstract class SVGPolylineElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable, SVGAnimatedPoints {
}

@native public abstract class SVGPolygonElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable, SVGAnimatedPoints {
}

@native public abstract class SVGTextContentElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable {
    open val textLength: SVGAnimatedLength
        get() = noImpl
    open val lengthAdjust: SVGAnimatedEnumeration
        get() = noImpl
    fun getNumberOfChars(): Int = noImpl
    fun getComputedTextLength(): Float = noImpl
    fun getSubStringLength(charnum: Int, nchars: Int): Float = noImpl
    fun getStartPositionOfChar(charnum: Int): SVGPoint = noImpl
    fun getEndPositionOfChar(charnum: Int): SVGPoint = noImpl
    fun getExtentOfChar(charnum: Int): SVGRect = noImpl
    fun getRotationOfChar(charnum: Int): Float = noImpl
    fun getCharNumAtPosition(point: SVGPoint): Int = noImpl
    fun selectSubString(charnum: Int, nchars: Int): Unit = noImpl

    companion object {
        val LENGTHADJUST_UNKNOWN: Short = 0
        val LENGTHADJUST_SPACING: Short = 1
        val LENGTHADJUST_SPACINGANDGLYPHS: Short = 2
    }
}

@native public abstract class SVGTextPositioningElement : SVGTextContentElement() {
    open val x: SVGAnimatedLengthList
        get() = noImpl
    open val y: SVGAnimatedLengthList
        get() = noImpl
    open val dx: SVGAnimatedLengthList
        get() = noImpl
    open val dy: SVGAnimatedLengthList
        get() = noImpl
    open val rotate: SVGAnimatedNumberList
        get() = noImpl
}

@native public abstract class SVGTextElement : SVGTextPositioningElement, SVGTransformable {
}

@native public abstract class SVGTSpanElement : SVGTextPositioningElement() {
}

@native public abstract class SVGTRefElement : SVGTextPositioningElement, SVGURIReference {
}

@native public abstract class SVGTextPathElement : SVGTextContentElement, SVGURIReference {
    open val startOffset: SVGAnimatedLength
        get() = noImpl
    open val method: SVGAnimatedEnumeration
        get() = noImpl
    open val spacing: SVGAnimatedEnumeration
        get() = noImpl

    companion object {
        val TEXTPATH_METHODTYPE_UNKNOWN: Short = 0
        val TEXTPATH_METHODTYPE_ALIGN: Short = 1
        val TEXTPATH_METHODTYPE_STRETCH: Short = 2
        val TEXTPATH_SPACINGTYPE_UNKNOWN: Short = 0
        val TEXTPATH_SPACINGTYPE_AUTO: Short = 1
        val TEXTPATH_SPACINGTYPE_EXACT: Short = 2
    }
}

@native public abstract class SVGAltGlyphElement : SVGTextPositioningElement, SVGURIReference {
    open var glyphRef: dynamic
        get() = noImpl
        set(value) = noImpl
    open var format: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGAltGlyphDefElement : SVGElement() {
}

@native public abstract class SVGAltGlyphItemElement : SVGElement() {
}

@native public abstract class SVGGlyphRefElement : SVGElement, SVGURIReference, SVGStylable {
    open var glyphRef: dynamic
        get() = noImpl
        set(value) = noImpl
    open var format: dynamic
        get() = noImpl
        set(value) = noImpl
    open var x: dynamic
        get() = noImpl
        set(value) = noImpl
    open var y: dynamic
        get() = noImpl
        set(value) = noImpl
    open var dx: dynamic
        get() = noImpl
        set(value) = noImpl
    open var dy: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGPaint : SVGColor() {
    open val paintType: Short
        get() = noImpl
    open val uri: String
        get() = noImpl
    fun setUri(uri: String): Unit = noImpl
    fun setPaint(paintType: Short, uri: String, rgbColor: String, iccColor: String): Unit = noImpl

    companion object {
        val SVG_PAINTTYPE_UNKNOWN: Short = 0
        val SVG_PAINTTYPE_RGBCOLOR: Short = 1
        val SVG_PAINTTYPE_RGBCOLOR_ICCCOLOR: Short = 2
        val SVG_PAINTTYPE_NONE: Short = 101
        val SVG_PAINTTYPE_CURRENTCOLOR: Short = 102
        val SVG_PAINTTYPE_URI_NONE: Short = 103
        val SVG_PAINTTYPE_URI_CURRENTCOLOR: Short = 104
        val SVG_PAINTTYPE_URI_RGBCOLOR: Short = 105
        val SVG_PAINTTYPE_URI_RGBCOLOR_ICCCOLOR: Short = 106
        val SVG_PAINTTYPE_URI: Short = 107
    }
}

@native public abstract class SVGMarkerElement : SVGElement, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGFitToViewBox {
    open val refX: SVGAnimatedLength
        get() = noImpl
    open val refY: SVGAnimatedLength
        get() = noImpl
    open val markerUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val markerWidth: SVGAnimatedLength
        get() = noImpl
    open val markerHeight: SVGAnimatedLength
        get() = noImpl
    open val orientType: SVGAnimatedEnumeration
        get() = noImpl
    open val orientAngle: SVGAnimatedAngle
        get() = noImpl
    fun setOrientToAuto(): Unit = noImpl
    fun setOrientToAngle(angle: SVGAngle): Unit = noImpl

    companion object {
        val SVG_MARKERUNITS_UNKNOWN: Short = 0
        val SVG_MARKERUNITS_USERSPACEONUSE: Short = 1
        val SVG_MARKERUNITS_STROKEWIDTH: Short = 2
        val SVG_MARKER_ORIENT_UNKNOWN: Short = 0
        val SVG_MARKER_ORIENT_AUTO: Short = 1
        val SVG_MARKER_ORIENT_ANGLE: Short = 2
    }
}

@native public abstract class SVGColorProfileElement : SVGElement, SVGURIReference, SVGRenderingIntent {
    open var local: String
        get() = noImpl
        set(value) = noImpl
    open var name: String
        get() = noImpl
        set(value) = noImpl
    open var renderingIntent: Short
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGColorProfileRule : SVGCSSRule, SVGRenderingIntent {
    open var src: dynamic
        get() = noImpl
        set(value) = noImpl
    open var name: dynamic
        get() = noImpl
        set(value) = noImpl
    open var renderingIntent: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public abstract class SVGGradientElement : SVGElement, SVGURIReference, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    open val gradientUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val gradientTransform: SVGAnimatedTransformList
        get() = noImpl
    open val spreadMethod: SVGAnimatedEnumeration
        get() = noImpl

    companion object {
        val SVG_SPREADMETHOD_UNKNOWN: Short = 0
        val SVG_SPREADMETHOD_PAD: Short = 1
        val SVG_SPREADMETHOD_REFLECT: Short = 2
        val SVG_SPREADMETHOD_REPEAT: Short = 3
    }
}

@native public abstract class SVGLinearGradientElement : SVGGradientElement() {
    open val x1: SVGAnimatedLength
        get() = noImpl
    open val y1: SVGAnimatedLength
        get() = noImpl
    open val x2: SVGAnimatedLength
        get() = noImpl
    open val y2: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGRadialGradientElement : SVGGradientElement() {
    open val cx: SVGAnimatedLength
        get() = noImpl
    open val cy: SVGAnimatedLength
        get() = noImpl
    open val r: SVGAnimatedLength
        get() = noImpl
    open val fx: SVGAnimatedLength
        get() = noImpl
    open val fy: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGStopElement : SVGElement, SVGStylable {
    open val offset: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGPatternElement : SVGElement, SVGURIReference, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGFitToViewBox, SVGUnitTypes {
    open val patternUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val patternContentUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val patternTransform: SVGAnimatedTransformList
        get() = noImpl
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGClipPathElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable, SVGUnitTypes {
    open val clipPathUnits: SVGAnimatedEnumeration
        get() = noImpl
}

@native public abstract class SVGMaskElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    open val maskUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val maskContentUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGFilterElement : SVGElement, SVGURIReference, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    open val filterUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val primitiveUnits: SVGAnimatedEnumeration
        get() = noImpl
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open val filterResX: SVGAnimatedInteger
        get() = noImpl
    open val filterResY: SVGAnimatedInteger
        get() = noImpl
    fun setFilterRes(filterResX: Int, filterResY: Int): Unit = noImpl
}

@native public abstract class SVGFilterPrimitiveStandardAttributes : SVGStylable() {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
    open val result: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGFEBlendElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val in2: SVGAnimatedString
        get() = noImpl
    open val mode: SVGAnimatedEnumeration
        get() = noImpl

    companion object {
        val SVG_FEBLEND_MODE_UNKNOWN: Short = 0
        val SVG_FEBLEND_MODE_NORMAL: Short = 1
        val SVG_FEBLEND_MODE_MULTIPLY: Short = 2
        val SVG_FEBLEND_MODE_SCREEN: Short = 3
        val SVG_FEBLEND_MODE_DARKEN: Short = 4
        val SVG_FEBLEND_MODE_LIGHTEN: Short = 5
    }
}

@native public abstract class SVGFEColorMatrixElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val type: SVGAnimatedEnumeration
        get() = noImpl
    open val values: SVGAnimatedNumberList
        get() = noImpl

    companion object {
        val SVG_FECOLORMATRIX_TYPE_UNKNOWN: Short = 0
        val SVG_FECOLORMATRIX_TYPE_MATRIX: Short = 1
        val SVG_FECOLORMATRIX_TYPE_SATURATE: Short = 2
        val SVG_FECOLORMATRIX_TYPE_HUEROTATE: Short = 3
        val SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA: Short = 4
    }
}

@native public abstract class SVGFEComponentTransferElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGComponentTransferFunctionElement : SVGElement() {
    open val type: SVGAnimatedEnumeration
        get() = noImpl
    open val tableValues: SVGAnimatedNumberList
        get() = noImpl
    open val slope: SVGAnimatedNumber
        get() = noImpl
    open val intercept: SVGAnimatedNumber
        get() = noImpl
    open val amplitude: SVGAnimatedNumber
        get() = noImpl
    open val exponent: SVGAnimatedNumber
        get() = noImpl
    open val offset: SVGAnimatedNumber
        get() = noImpl

    companion object {
        val SVG_FECOMPONENTTRANSFER_TYPE_UNKNOWN: Short = 0
        val SVG_FECOMPONENTTRANSFER_TYPE_IDENTITY: Short = 1
        val SVG_FECOMPONENTTRANSFER_TYPE_TABLE: Short = 2
        val SVG_FECOMPONENTTRANSFER_TYPE_DISCRETE: Short = 3
        val SVG_FECOMPONENTTRANSFER_TYPE_LINEAR: Short = 4
        val SVG_FECOMPONENTTRANSFER_TYPE_GAMMA: Short = 5
    }
}

@native public abstract class SVGFEFuncRElement : SVGComponentTransferFunctionElement() {
}

@native public abstract class SVGFEFuncGElement : SVGComponentTransferFunctionElement() {
}

@native public abstract class SVGFEFuncBElement : SVGComponentTransferFunctionElement() {
}

@native public abstract class SVGFEFuncAElement : SVGComponentTransferFunctionElement() {
}

@native public abstract class SVGFECompositeElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val in2: SVGAnimatedString
        get() = noImpl
    open val operator: SVGAnimatedEnumeration
        get() = noImpl
    open val k1: SVGAnimatedNumber
        get() = noImpl
    open val k2: SVGAnimatedNumber
        get() = noImpl
    open val k3: SVGAnimatedNumber
        get() = noImpl
    open val k4: SVGAnimatedNumber
        get() = noImpl

    companion object {
        val SVG_FECOMPOSITE_OPERATOR_UNKNOWN: Short = 0
        val SVG_FECOMPOSITE_OPERATOR_OVER: Short = 1
        val SVG_FECOMPOSITE_OPERATOR_IN: Short = 2
        val SVG_FECOMPOSITE_OPERATOR_OUT: Short = 3
        val SVG_FECOMPOSITE_OPERATOR_ATOP: Short = 4
        val SVG_FECOMPOSITE_OPERATOR_XOR: Short = 5
        val SVG_FECOMPOSITE_OPERATOR_ARITHMETIC: Short = 6
    }
}

@native public abstract class SVGFEConvolveMatrixElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val orderX: SVGAnimatedInteger
        get() = noImpl
    open val orderY: SVGAnimatedInteger
        get() = noImpl
    open val kernelMatrix: SVGAnimatedNumberList
        get() = noImpl
    open val divisor: SVGAnimatedNumber
        get() = noImpl
    open val bias: SVGAnimatedNumber
        get() = noImpl
    open val targetX: SVGAnimatedInteger
        get() = noImpl
    open val targetY: SVGAnimatedInteger
        get() = noImpl
    open val edgeMode: SVGAnimatedEnumeration
        get() = noImpl
    open val kernelUnitLengthX: SVGAnimatedNumber
        get() = noImpl
    open val kernelUnitLengthY: SVGAnimatedNumber
        get() = noImpl
    open val preserveAlpha: SVGAnimatedBoolean
        get() = noImpl

    companion object {
        val SVG_EDGEMODE_UNKNOWN: Short = 0
        val SVG_EDGEMODE_DUPLICATE: Short = 1
        val SVG_EDGEMODE_WRAP: Short = 2
        val SVG_EDGEMODE_NONE: Short = 3
    }
}

@native public abstract class SVGFEDiffuseLightingElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val surfaceScale: SVGAnimatedNumber
        get() = noImpl
    open val diffuseConstant: SVGAnimatedNumber
        get() = noImpl
    open val kernelUnitLengthX: SVGAnimatedNumber
        get() = noImpl
    open val kernelUnitLengthY: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFEDistantLightElement : SVGElement() {
    open val azimuth: SVGAnimatedNumber
        get() = noImpl
    open val elevation: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFEPointLightElement : SVGElement() {
    open val x: SVGAnimatedNumber
        get() = noImpl
    open val y: SVGAnimatedNumber
        get() = noImpl
    open val z: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFESpotLightElement : SVGElement() {
    open val x: SVGAnimatedNumber
        get() = noImpl
    open val y: SVGAnimatedNumber
        get() = noImpl
    open val z: SVGAnimatedNumber
        get() = noImpl
    open val pointsAtX: SVGAnimatedNumber
        get() = noImpl
    open val pointsAtY: SVGAnimatedNumber
        get() = noImpl
    open val pointsAtZ: SVGAnimatedNumber
        get() = noImpl
    open val specularExponent: SVGAnimatedNumber
        get() = noImpl
    open val limitingConeAngle: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFEDisplacementMapElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val in2: SVGAnimatedString
        get() = noImpl
    open val scale: SVGAnimatedNumber
        get() = noImpl
    open val xChannelSelector: SVGAnimatedEnumeration
        get() = noImpl
    open val yChannelSelector: SVGAnimatedEnumeration
        get() = noImpl

    companion object {
        val SVG_CHANNEL_UNKNOWN: Short = 0
        val SVG_CHANNEL_R: Short = 1
        val SVG_CHANNEL_G: Short = 2
        val SVG_CHANNEL_B: Short = 3
        val SVG_CHANNEL_A: Short = 4
    }
}

@native public abstract class SVGFEFloodElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
}

@native public abstract class SVGFEGaussianBlurElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val stdDeviationX: SVGAnimatedNumber
        get() = noImpl
    open val stdDeviationY: SVGAnimatedNumber
        get() = noImpl
    fun setStdDeviation(stdDeviationX: Float, stdDeviationY: Float): Unit = noImpl
}

@native public abstract class SVGFEImageElement : SVGElement, SVGURIReference, SVGLangSpace, SVGExternalResourcesRequired, SVGFilterPrimitiveStandardAttributes {
    open val preserveAspectRatio: SVGAnimatedPreserveAspectRatio
        get() = noImpl
}

@native public abstract class SVGFEMergeElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
}

@native public abstract class SVGFEMergeNodeElement : SVGElement() {
    open val in1: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGFEMorphologyElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val operator: SVGAnimatedEnumeration
        get() = noImpl
    open val radiusX: SVGAnimatedNumber
        get() = noImpl
    open val radiusY: SVGAnimatedNumber
        get() = noImpl

    companion object {
        val SVG_MORPHOLOGY_OPERATOR_UNKNOWN: Short = 0
        val SVG_MORPHOLOGY_OPERATOR_ERODE: Short = 1
        val SVG_MORPHOLOGY_OPERATOR_DILATE: Short = 2
    }
}

@native public abstract class SVGFEOffsetElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val dx: SVGAnimatedNumber
        get() = noImpl
    open val dy: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFESpecularLightingElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
    open val surfaceScale: SVGAnimatedNumber
        get() = noImpl
    open val specularConstant: SVGAnimatedNumber
        get() = noImpl
    open val specularExponent: SVGAnimatedNumber
        get() = noImpl
    open val kernelUnitLengthX: SVGAnimatedNumber
        get() = noImpl
    open val kernelUnitLengthY: SVGAnimatedNumber
        get() = noImpl
}

@native public abstract class SVGFETileElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val in1: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGFETurbulenceElement : SVGElement, SVGFilterPrimitiveStandardAttributes {
    open val baseFrequencyX: SVGAnimatedNumber
        get() = noImpl
    open val baseFrequencyY: SVGAnimatedNumber
        get() = noImpl
    open val numOctaves: SVGAnimatedInteger
        get() = noImpl
    open val seed: SVGAnimatedNumber
        get() = noImpl
    open val stitchTiles: SVGAnimatedEnumeration
        get() = noImpl
    open val type: SVGAnimatedEnumeration
        get() = noImpl

    companion object {
        val SVG_TURBULENCE_TYPE_UNKNOWN: Short = 0
        val SVG_TURBULENCE_TYPE_FRACTALNOISE: Short = 1
        val SVG_TURBULENCE_TYPE_TURBULENCE: Short = 2
        val SVG_STITCHTYPE_UNKNOWN: Short = 0
        val SVG_STITCHTYPE_STITCH: Short = 1
        val SVG_STITCHTYPE_NOSTITCH: Short = 2
    }
}

@native public abstract class SVGCursorElement : SVGElement, SVGURIReference, SVGTests, SVGExternalResourcesRequired {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
}

@native public abstract class SVGAElement : SVGElement, SVGURIReference, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val target: SVGAnimatedString
        get() = noImpl
}

@native public abstract class SVGViewElement : SVGElement, SVGExternalResourcesRequired, SVGFitToViewBox, SVGZoomAndPan {
    open val viewTarget: SVGStringList
        get() = noImpl
}

@native public abstract class SVGScriptElement : SVGElement, SVGURIReference, SVGExternalResourcesRequired {
    open var type: dynamic
        get() = noImpl
        set(value) = noImpl
}

@native public open class SVGZoomEvent : UIEvent(noImpl, noImpl) {
    open val zoomRectScreen: SVGRect
        get() = noImpl
    open val previousScale: Float
        get() = noImpl
    open val previousTranslate: SVGPoint
        get() = noImpl
    open val newScale: Float
        get() = noImpl
    open val newTranslate: SVGPoint
        get() = noImpl
}

@native public abstract class ElementTimeControl {
    fun beginElement(): Unit = noImpl
    fun beginElementAt(offset: Float): Unit = noImpl
    fun endElement(): Unit = noImpl
    fun endElementAt(offset: Float): Unit = noImpl
}

@native public open class TimeEvent : Event(noImpl, noImpl) {
    open val view: dynamic
        get() = noImpl
    open val detail: Int
        get() = noImpl
    fun initTimeEvent(typeArg: String, viewArg: dynamic, detailArg: Int): Unit = noImpl
}

@native public abstract class SVGAnimationElement : SVGElement, SVGTests, SVGExternalResourcesRequired, ElementTimeControl {
    open val targetElement: SVGElement
        get() = noImpl
    fun getStartTime(): Float = noImpl
    fun getCurrentTime(): Float = noImpl
    fun getSimpleDuration(): Float = noImpl
}

@native public abstract class SVGAnimateElement : SVGAnimationElement, SVGStylable {
}

@native public abstract class SVGSetElement : SVGAnimationElement() {
}

@native public abstract class SVGAnimateMotionElement : SVGAnimationElement() {
}

@native public abstract class SVGMPathElement : SVGElement, SVGURIReference, SVGExternalResourcesRequired {
}

@native public abstract class SVGAnimateColorElement : SVGAnimationElement, SVGStylable {
}

@native public abstract class SVGAnimateTransformElement : SVGAnimationElement() {
}

@native public abstract class SVGFontElement : SVGElement, SVGExternalResourcesRequired, SVGStylable {
}

@native public abstract class SVGGlyphElement : SVGElement, SVGStylable {
}

@native public abstract class SVGMissingGlyphElement : SVGElement, SVGStylable {
}

@native public abstract class SVGHKernElement : SVGElement() {
}

@native public abstract class SVGVKernElement : SVGElement() {
}

@native public abstract class SVGFontFaceElement : SVGElement() {
}

@native public abstract class SVGFontFaceSrcElement : SVGElement() {
}

@native public abstract class SVGFontFaceUriElement : SVGElement() {
}

@native public abstract class SVGFontFaceFormatElement : SVGElement() {
}

@native public abstract class SVGFontFaceNameElement : SVGElement() {
}

@native public abstract class SVGMetadataElement : SVGElement() {
}

@native public abstract class SVGForeignObjectElement : SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable {
    open val x: SVGAnimatedLength
        get() = noImpl
    open val y: SVGAnimatedLength
        get() = noImpl
    open val width: SVGAnimatedLength
        get() = noImpl
    open val height: SVGAnimatedLength
        get() = noImpl
}

