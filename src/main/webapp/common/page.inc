<c:if test="${pageModel.totalPage > 0}">
	<c:set var="href" value="${pageHref}?" />
	<div class="blockSearch">
		<div style="margin-top: -2px;" class="right">
			共<b class="org">${pageModel.totalCount}</b>条记录&nbsp;每页<b
			class="org">${pageModel.pageSize}</b>条&nbsp;共<b class="org">${pageModel.totalPage}</b>页
			<p class="page">
				<c:if test="${pageModel.getPreviousPage() > 0}">
					<a href="${href}?p=1" title="首页">首页<</a>
					<a href="${href}?p=${pageModel.getPreviousPage()}" title="上一页">上一页</a>
				</c:if>
				<c:forEach begin="${pageModel.getBeginPage()}"
				end="${pageModel.getEndPage()}" step="1" var="p">
					<c:choose>
						<c:when test="${p == pageModel.currentPage}">
							<strong>${p}</strong>
						</c:when>
						<c:otherwise>

							<a href="${href}p=${p}">${p}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageModel.getNextPage() > 0}">
					<a href="${href}p=${pageModel.getNextPage()}" title="下一页">下一页</a>
					<a href="${href}p=${pageModel.totalPage}" title="尾页">尾页</a>
				</c:if>
			</p>
		</div>
	</div>
</c:if>