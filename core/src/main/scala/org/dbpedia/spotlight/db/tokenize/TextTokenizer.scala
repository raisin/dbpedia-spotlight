package org.dbpedia.spotlight.db.tokenize

import java.util.Locale

import org.dbpedia.spotlight.db.memory.DummyTokenTypeStore
import org.dbpedia.spotlight.db.model.{Stemmer, TextTokenizer, TokenTypeStore}
import org.dbpedia.spotlight.db.stem.SnowballStemmer

object TextTokenizer {

  def fromParameters(locale: Locale, stemmerString: String, stopWordsFile: String, modelFolder: String, tokenStoreP: TokenTypeStore): TextTokenizer = {
    val tokenTypeStore = if (tokenStoreP == null) new DummyTokenTypeStore else tokenStoreP


    val stopWords =  scala.io.Source.fromFile(stopWordsFile).getLines().map(_.trim()).toSet

    val stemmer = stemmerString match {
      case "" => new Stemmer
      case s => new SnowballStemmer(s)
    }

    new LanguageIndependentTokenizer(
      stopWords,
      stemmer,
      locale,
      tokenTypeStore
    )

  }

}
