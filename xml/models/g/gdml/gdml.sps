<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="gdml.xsd" workingxmlfile="instance\dd\exp.gdml" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8">
	<nspair prefix="g" uri="http://www.bodz.net/xml/current/gdml"/>
	<nspair prefix="xsf" uri="http://www.bodz.net/xml/current/xsf"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<paragraph paragraphtag="h2">
				<children>
					<text fixtext="Language Definition of "/>
					<template>
						<match match="g:language"/>
						<children>
							<template>
								<match match="@name"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</paragraph>
			<template>
				<match match="g:language"/>
				<children>
					<line>
						<properties color="black" size="1"/>
					</line>
					<template>
						<match match="@info"/>
						<children>
							<paragraph paragraphtag="p">
								<properties align="right"/>
								<children>
									<xpath allchildren="1">
										<styles font-style="italic"/>
									</xpath>
								</children>
							</paragraph>
						</children>
					</template>
				</children>
			</template>
			<template>
				<match match="g:language"/>
				<children>
					<template>
						<match match="g:symbol"/>
						<children>
							<table dynamic="1">
								<properties border="1"/>
								<children>
									<tableheader>
										<children>
											<tablerow>
												<children>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Symbol">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Extends">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Association">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Priority">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Type">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<text fixtext="Id">
																<styles font-size="larger" font-weight="bold"/>
															</text>
														</children>
													</tablecol>
												</children>
											</tablerow>
										</children>
									</tableheader>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
														<children>
															<template>
																<match match="@name"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<children>
															<template>
																<match match="@extends"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<children>
															<template>
																<match match="@assoc"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<children>
															<template>
																<match match="@priority"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<children>
															<template>
																<match match="@type"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<children>
															<template>
																<match match="@id"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecol>
														<styles border-bottom-style="solid" border-bottom-width="medium"/>
													</tablecol>
													<tablecol>
														<styles border-bottom-style="solid"/>
														<properties colspan="5"/>
														<children>
															<template>
																<match match="@info"/>
																<children>
																	<xpath allchildren="1"/>
																</children>
															</template>
														</children>
													</tablecol>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
						</children>
					</template>
				</children>
			</template>
			<line>
				<properties color="black" size="1"/>
			</line>
			<template>
				<match match="g:language"/>
				<children>
					<template>
						<match match="g:symbol"/>
						<children>
							<paragraph paragraphtag="h3">
								<children>
									<text fixtext="Symbol "/>
									<template>
										<match match="@name"/>
										<children>
											<xpath allchildren="1"/>
										</children>
									</template>
								</children>
							</paragraph>
							<template>
								<match match="g:syntax"/>
								<children>
									<list dynamic="1" ordered="1">
										<styles margin-bottom="0" margin-top="0"/>
										<properties start="1" type="i"/>
										<children>
											<listrow>
												<children>
													<template>
														<match match="@name"/>
														<children>
															<xpath allchildren="1"/>
														</children>
													</template>
												</children>
											</listrow>
										</children>
									</list>
								</children>
							</template>
							<line>
								<properties color="black" size="1"/>
							</line>
						</children>
					</template>
				</children>
			</template>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>
