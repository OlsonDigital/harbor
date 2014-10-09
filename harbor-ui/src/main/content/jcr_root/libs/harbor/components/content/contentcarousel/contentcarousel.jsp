<%@include file="/libs/harbor/components/global.jsp" %>

<div id="${carousel.uniqueIdentifier}-carousel" class="${carousel.cssClass}" <c:if test="${isPreviewMode || isPublish}">data-ride="carousel"</c:if>>

    <c:if test="${carousel.showSlideSelectorControls}">
        <cq:include script="slideselectorcontrols.jsp" />
    </c:if>
    <cq:include script="slides.jsp" />
    <c:if test="${carousel.showPreviousAndNextControls}">
        <cq:include script="previousandnextcontrols.jsp" />
    </c:if>

</div>