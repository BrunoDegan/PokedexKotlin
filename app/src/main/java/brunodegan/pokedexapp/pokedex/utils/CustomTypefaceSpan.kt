package brunodegan.pokedexapp.pokedex.utils

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * Created by brunodegan on 9/2/17.
 */
class CustomTypefaceSpan( typefaceSpan: Typeface) : TypefaceSpan(""){
		
		val newTypeFace = typefaceSpan
	
	override fun updateDrawState(ds: TextPaint?) {
		super.updateDrawState(ds)
	}
	
	override fun updateMeasureState(paint: TextPaint?) {
		super.updateMeasureState(paint)
	}
	
	private fun applyCustomTypeFace(paint : Paint, typeface : Typeface) {
		val lastStyle : Int
		val lastTypeface : Typeface = paint.typeface
		
		if(lastTypeface == null) {
			lastStyle = 0
		} else {
			lastStyle = lastTypeface.style
		}
		
		/* PARA VERIFICAR A COMPATIBILIDADE DE ESTILOS */
		val fakeStyleCheck = lastStyle and lastTypeface.style.inv()
		
		/*
         * VERIFICA SE A FONTE MAIS ATUAL JÁ ESTÁ DE ACORDO
         * COM A ANTERIOR EM TERMOS DE "TEXTO EM NEGRITO",
         * CASO NÃO, ATUALIZA.
         * */
		if(fakeStyleCheck and Typeface.BOLD != 0) {
			paint.isFakeBoldText = true
		}
		
		/*
	  * VERIFICA SE A FONTE MAIS ATUAL JÁ ESTÁ DE ACORDO
	  * COM A ANTERIOR EM TERMOS DE "TEXTO EM ITÁLICO",
	  * CASO NÃO, ATUALIZA.
	  * */
		
		if(fakeStyleCheck and Typeface.ITALIC != 0) {
			paint.textSkewX = -0.25f
		}
		
		/** APLICA TYPEFACE NA FONTE **/
		
		paint.setTypeface(typeface)
	}
}
