package net.bodz.violet.art.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.art.Artifact;

/**
 * 定义生产过程或供销中使用的制品（成品或半成品）。<br>
 * 也叫SKU（库存保管单元）比如一件衬衫，按颜色、尺码可以定义一系列物料。
 * 
 * @label 物料
 * 
 * @rel artcat/: 管理物料分类
 * @rel uom/: 管理计量单位
 * @rel stdoc/new/?cat=1202: 采购入库
 * @rel stdoc/new/?cat=1208: 库存盘点
 * 
 * @see <a href="http://www.bensino.com/news/sInstitute/2/2012/1212/40641.html">SKU不是越多越好</a>
 * @see <a href="http://www.360doc.com/content/08/0109/14/25392_958214.shtml">物料编码规划</a>
 * @see <a href="http://baike.baidu.com/view/395868.htm">什么是物料清单（BOM）</a>
 * @see <a href="http://book.ebusinessreview.cn/bookpartinfo-71335.html">几种不同的 BOM 拓扑结构</a>
 */
@ObjectType(Artifact.class)
public class ArtifactIndex
        extends CoIndex<Artifact, ArtifactMask> {

}
