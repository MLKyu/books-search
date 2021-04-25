package com.alansoft.kapaycote.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class BooksSearchResponse(
    val meta: Meta?,
    val documents: List<Document?>?
)

data class Meta(
    @SerializedName("total_count")
    var totalCount: Int,
    @SerializedName("pageable_count")
    var pageableCount: Int,
    @SerializedName("is_end")
    var isEnd: Boolean
)


/**
 * title	String	도서 제목
contents	String	도서 소개
url	String	도서 상세 URL
isbn	String	ISBN10(10자리) 또는 ISBN13(13자리) 형식의 국제 표준 도서번호(International Standard Book Number)
ISBN10 또는 ISBN13 중 하나 이상 포함
두 값이 모두 제공될 경우 공백(' ')으로 구분
datetime	Datetime	도서 출판날짜, ISO 8601 형식
[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
authors	String[]	도서 저자 리스트
publisher	String	도서 출판사
translators	String[]	도서 번역자 리스트
price	Integer	도서 정가
sale_price	Integer	도서 판매가
thumbnail	String	도서 표지 미리보기 URL
status	String	도서 판매 상태 정보 (정상, 품절, 절판 등)
상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장
 */
@Parcelize
data class Document(
    val title: String?,
    val contents: String?,
    val url: String?,
    val isbn: String?,
    val datetime: String?,
    val authors: List<String?>?,
    val publisher: String?,
    val translators: List<String?>?,
    val price: Int,
    @SerializedName("sale_price")
    val salePrice: Int,
    val thumbnail: String?,
    val status: String?
) : Parcelable