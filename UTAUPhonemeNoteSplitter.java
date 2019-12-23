// Import classes
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
   Takes the phonemes of each note in an
   UTAU file and splits up the note.
   @author J.P. O'Malley
*/

public class UTAUPhonemeNoteSplitter {

   private static String replaceLast(String str, String target, String replacement) {
      
      int len = str.length(),
          start = str.lastIndexOf(target),
          end = start + target.length();
          
      if (start != -1) {
      
         String newStr = new String("");
         
         if (start != 0) {
            newStr = str.substring(0, start);
         }
         
         newStr = newStr.concat(replacement);
         
         if (end != len) {
            
            newStr = newStr.concat(str.substring(end, len));
         }
         
         return newStr;
      }
      
      return new String(str);
   }
   
   public static void main(String[] args) throws FileNotFoundException {
      
      // Declare constants
      boolean KANA_MODE;
      if (args[0].equals("-j") || args[0].equals("--kana")) {
         KANA_MODE = true;
      }
      else {
         KANA_MODE = false;
      }
      
      String FILENAME;
      if (KANA_MODE) {
         FILENAME = args[1];
      }
      else {
         FILENAME = args[0];
      }
      
      String FIRST_NOTE = "[#0000]",
             FIRST_LINE = "[#VERSION]",
             LAST_LINE = "[#TRACKEND]";
      
      // Open the file
      File inFile = new File(FILENAME);
      Scanner file = new Scanner(inFile);
      file.useDelimiter("\r\n");
      
      String fileInfo = "";
      
      // Read the file
      String line = "";
      
      while (file.hasNext() && !(line.equals(FIRST_NOTE))) {
         
         line = file.next();
         
         if (!(line.equals(FIRST_NOTE))) {
            if (!(line.equals(FIRST_LINE))) {
               fileInfo += "\r\n";
            }
            
            fileInfo += line;
         }
      }
      
      // Get the notes
      ArrayList<Note> notes = new ArrayList<Note>(0);
      Note currentNote = new Note(FIRST_NOTE);
      
      while (file.hasNext()) {
         
         line = file.next(); // Read next line
         
         if (line.startsWith(Note.DELTA)) {
            currentNote.setDelta(line);
         }
         else if (line.startsWith(Note.DURATION)) {
            currentNote.setDuration(line);
         }
         else if (line.startsWith(Note.LENGTH)) {
            currentNote.setLength(line);
         }
         else if (line.startsWith(Note.LYRIC)) {
            currentNote.setLyric(line);
         }
         else if (line.startsWith(Note.TEMPO)) {
            currentNote.setTempo(line);
         }
         else if (line.startsWith(Note.NOTE_NUM)) {
            currentNote.setNoteNum(line);
         }
         else if (line.startsWith(Note.PRE_UTTERANCE)) {
            currentNote.setPreUtterance(line);
         }
         else if (line.startsWith(Note.VOICE_OVERLAP)) {
            currentNote.setVoiceOverlap(line);
         }
         else if (line.startsWith(Note.VELOCITY)) {
            currentNote.setVelocity(line);
         }
         else if (line.startsWith(Note.START_POINT)) {
            currentNote.setStartPoint(line);
         }
         else if (line.startsWith(Note.INTENSITY)) {
            currentNote.setIntensity(line);
         }
         else if (line.startsWith(Note.MODULATION)) {
            currentNote.setModulation(line);
         }
         else if (line.startsWith(Note.FLAGS)) {
            currentNote.setFlags(line);
         }
         else if (line.startsWith(Note.PBS)) {
            currentNote.setPBS(line);
         }
         else if (line.startsWith(Note.PBW)) {
            currentNote.setPBW(line);
         }
         else if (line.startsWith(Note.PBY)) {
            currentNote.setPBY(line);
         }
         else if (line.startsWith(Note.ENVELOPE)) {
            currentNote.setEnvelope(line);
         }
         else if (line.startsWith(Note.VBR)) {
            currentNote.setVBR(line);
         }
         else if (line.startsWith("[#")) {
            notes.add(currentNote);
            
            if (! line.equals(LAST_LINE)) {
               currentNote = new Note(line);
            }
         }
      }
      
      // Close the file
      file.close();
      
      // Declare transcription constants
      
      final ArrayList<String> PHONEMES = new ArrayList<String>(),
                              CONSONANTS = new ArrayList<String>(),
                              VOWELS = new ArrayList<String>(),
                              SEMIVOWELS = new ArrayList<String>();
      
      CONSONANTS.add("4");
      CONSONANTS.add("5");
      CONSONANTS.add("B");
      CONSONANTS.add("b");
      CONSONANTS.add("D");
      CONSONANTS.add("d");
      CONSONANTS.add("dZ");
      CONSONANTS.add("f");
      CONSONANTS.add("g");
      CONSONANTS.add("h");
      CONSONANTS.add("j");
      CONSONANTS.add("k");
      CONSONANTS.add("k_h");
      CONSONANTS.add("l");
      CONSONANTS.add("M\\");
      CONSONANTS.add("M\\_B");
      CONSONANTS.add("M\\_B\\");
      CONSONANTS.add("m");
      CONSONANTS.add("N");
      CONSONANTS.add("N\\");
      CONSONANTS.add("n");
      CONSONANTS.add("p");
      CONSONANTS.add("p\\");
      CONSONANTS.add("p_h");
      CONSONANTS.add("r\\");
      CONSONANTS.add("S");
      CONSONANTS.add("s");
      CONSONANTS.add("T");
      CONSONANTS.add("t");
      CONSONANTS.add("tS");
      CONSONANTS.add("t_h");
      CONSONANTS.add("v");
      CONSONANTS.add("W");
      CONSONANTS.add("w");
      CONSONANTS.add("w_B");
      CONSONANTS.add("w_B\\");
      CONSONANTS.add("Z");
      CONSONANTS.add("z");
      CONSONANTS.add("\\?");
      
      SEMIVOWELS.add("-a_^");
      SEMIVOWELS.add("-e_^");
      SEMIVOWELS.add("-i_^");
      SEMIVOWELS.add("-M_^");
      SEMIVOWELS.add("-N\\=_^");
      SEMIVOWELS.add("-o_^");
      CONSONANTS.addAll(SEMIVOWELS);
      
      VOWELS.add("{");
      VOWELS.add("V");
      VOWELS.add("@");
      VOWELS.add("@`");
      VOWELS.add("3`");
      VOWELS.add("A");
      VOWELS.add("Ar\\");
      VOWELS.add("E");
      VOWELS.add("Er\\");
      VOWELS.add("I");
      VOWELS.add("I\\");
      VOWELS.add("Ir\\");
      VOWELS.add("M");
      VOWELS.add("M_B");
      VOWELS.add("M_B\\");
      VOWELS.add("N=");
      VOWELS.add("N\\=");
      VOWELS.add("O");
      VOWELS.add("OI");
      VOWELS.add("Or\\");
      VOWELS.add("U");
      VOWELS.add("U\\");
      VOWELS.add("Ur\\");
      VOWELS.add("a");
      VOWELS.add("aI");
      VOWELS.add("aIr\\");
      VOWELS.add("aU");
      VOWELS.add("e");
      VOWELS.add("eI");
      VOWELS.add("i");
      VOWELS.add("m=");
      VOWELS.add("n=");
      VOWELS.add("o");
      VOWELS.add("oU");
      VOWELS.add("u");
      VOWELS.add("(...)");
      
      int origSize = VOWELS.size();
      
      for (int i = 0; i < origSize; i++) {
         
         String vowel = VOWELS.get(i);
         
         VOWELS.add("-" + vowel);
         VOWELS.add(vowel + "-");
         VOWELS.add("-" + vowel + "-");
      }
      
      PHONEMES.addAll(CONSONANTS);
      PHONEMES.addAll(VOWELS);
      
      
      // If Japanese
      if (KANA_MODE) {
      
         // Declare additional transcription constants
         String N_ALVEOLAR = "n",
                N_BILABIAL = "m",
                N_VELAR = "N",
                N_OTHER = "N\\";
         
         final ArrayList<String> NASALS = new ArrayList<String>(),
                                 I_VOWELS = new ArrayList<String>(),
                                 U_VOWELS = new ArrayList<String>(),
                                 ALVEOLARS = new ArrayList<String>(),
                                 BILABIALS = new ArrayList<String>(),
                                 VELARS = new ArrayList<String>();
         
         ALVEOLARS.add("4");
         ALVEOLARS.add("d");
         ALVEOLARS.add("dZ");
         ALVEOLARS.add("n");
         ALVEOLARS.add("n=");
         ALVEOLARS.add("n=-");
         ALVEOLARS.add("-n=");
         ALVEOLARS.add("-n=-");
         ALVEOLARS.add("t");
         ALVEOLARS.add("tS");
         ALVEOLARS.add("z");
         
         BILABIALS.add("b");
         BILABIALS.add("m");
         BILABIALS.add("m=");
         BILABIALS.add("m=-");
         BILABIALS.add("-m=");
         BILABIALS.add("-m=-");
         BILABIALS.add("p");
         
         VELARS.add("g");
         VELARS.add("k");
         VELARS.add("N");
         VELARS.add("N=");
         VELARS.add("N=-");
         VELARS.add("-N=");
         VELARS.add("-N=-");
                
         NASALS.add("N=");
         NASALS.add("N\\=");
         NASALS.add("n=");
         NASALS.add("m=");
         
         I_VOWELS.add("i");
         U_VOWELS.add("M");
         
         origSize = NASALS.size();
         for (int i = 0; i < origSize; i++) {
            
            String phoneme = NASALS.get(i);
            
            NASALS.add("-" + phoneme);
            NASALS.add(phoneme + "-");
            NASALS.add("-" + phoneme + "-");
         }
         
         origSize = I_VOWELS.size();
         for (int i = 0; i < origSize; i++) {
            
            String phoneme = I_VOWELS.get(i);
            
            I_VOWELS.add("-" + phoneme);
            I_VOWELS.add(phoneme + "-");
            I_VOWELS.add("-" + phoneme + "-");
         }
         
         origSize = U_VOWELS.size();
         for (int i = 0; i < origSize; i++) {
            
            String phoneme = U_VOWELS.get(i);
            
            U_VOWELS.add("-" + phoneme);
            U_VOWELS.add(phoneme + "-");
            U_VOWELS.add("-" + phoneme + "-");
         }
         
         final ArrayList<String> KANA = new ArrayList<String>(),
                                 TRANS = new ArrayList<String>(),
                                 PRE = new ArrayList<String>();
         
         KANA.add("あ"); TRANS.add("a");
         KANA.add("い"); TRANS.add("i");
         KANA.add("いぇ"); TRANS.add("j e");
         KANA.add("う"); TRANS.add("M");
         KANA.add("ヴ"); TRANS.add("B M");
         KANA.add("ヴぁ"); TRANS.add("B a");
         KANA.add("ヴァ"); TRANS.add("B a");
         KANA.add("うぃ"); TRANS.add("M\\ i");
         KANA.add("ヴぃ"); TRANS.add("B i");
         KANA.add("ヴィ"); TRANS.add("B i");
         KANA.add("うぇ"); TRANS.add("M\\ e");
         KANA.add("ヴぇ"); TRANS.add("B e");
         KANA.add("ヴェ"); TRANS.add("B e");
         KANA.add("うぉ"); TRANS.add("M\\ o");
         KANA.add("ヴぉ"); TRANS.add("B o");
         KANA.add("ヴォ"); TRANS.add("B o");
         KANA.add("え"); TRANS.add("e");
         KANA.add("お"); TRANS.add("o");
         KANA.add("を"); TRANS.add("o");
         KANA.add("か"); TRANS.add("k a");
         KANA.add("カ"); TRANS.add("N a");
         KANA.add("ガ"); TRANS.add("N a");
         KANA.add("が"); TRANS.add("g a");
         KANA.add("き"); TRANS.add("k j i");
         KANA.add("キ"); TRANS.add("N j i");
         KANA.add("ギ"); TRANS.add("N j i");
         KANA.add("ぎ"); TRANS.add("g j i");
         KANA.add("きぇ"); TRANS.add("k j e");
         KANA.add("ぎぇ"); TRANS.add("g j e");
         KANA.add("きゃ"); TRANS.add("k j a");
         KANA.add("ぎゃ"); TRANS.add("g j a");
         KANA.add("きゅ"); TRANS.add("k j M");
         KANA.add("ぎゅ"); TRANS.add("g j M");
         KANA.add("きょ"); TRANS.add("k j o");
         KANA.add("ぎょ"); TRANS.add("g j o");
         KANA.add("く"); TRANS.add("k M");
         KANA.add("ク"); TRANS.add("N M");
         KANA.add("グ"); TRANS.add("N M");
         KANA.add("ぐ"); TRANS.add("g M");
         KANA.add("け"); TRANS.add("k j e");
         KANA.add("ケ"); TRANS.add("N j e");
         KANA.add("ゲ"); TRANS.add("N j e");
         KANA.add("げ"); TRANS.add("g j e");
         KANA.add("こ"); TRANS.add("k o");
         KANA.add("コ"); TRANS.add("N o");
         KANA.add("ゴ"); TRANS.add("N o");
         KANA.add("ご"); TRANS.add("g o");
         KANA.add("さ"); TRANS.add("s a");
         KANA.add("ざ"); TRANS.add("z a");
         KANA.add("し"); TRANS.add("S j i");
         KANA.add("じ"); TRANS.add("dZ j i");
         KANA.add("ぢ"); TRANS.add("dZ j i");
         KANA.add("しぇ"); TRANS.add("S j e");
         KANA.add("じぇ"); TRANS.add("dZ j e");
         KANA.add("しゃ"); TRANS.add("S j a");
         KANA.add("じゃ"); TRANS.add("dZ j a");
         KANA.add("しゅ"); TRANS.add("S j M");
         KANA.add("じゅ"); TRANS.add("dZ j M");
         KANA.add("しょ"); TRANS.add("S j o");
         KANA.add("じょ"); TRANS.add("dZ j o");
         KANA.add("す"); TRANS.add("s M");
         KANA.add("ず"); TRANS.add("z M");
         KANA.add("づ"); TRANS.add("z M");
         KANA.add("すぃ"); TRANS.add("s i");
         KANA.add("ずぃ"); TRANS.add("z i");
         KANA.add("せ"); TRANS.add("s e");
         KANA.add("ぜ"); TRANS.add("z e");
         KANA.add("そ"); TRANS.add("s o");
         KANA.add("ぞ"); TRANS.add("z o");
         KANA.add("た"); TRANS.add("t a");
         KANA.add("だ"); TRANS.add("d a");
         KANA.add("ち"); TRANS.add("tS j i");
         KANA.add("ちぇ"); TRANS.add("tS j e");
         KANA.add("ちゃ"); TRANS.add("tS j a");
         KANA.add("ちゅ"); TRANS.add("tS j M");
         KANA.add("ちょ"); TRANS.add("tS j o");
         KANA.add("つ"); TRANS.add("t s M");
         KANA.add("つぁ"); TRANS.add("t s a");
         KANA.add("つぃ"); TRANS.add("t s i");
         KANA.add("つぇ"); TRANS.add("t s e");
         KANA.add("つぉ"); TRANS.add("t s o");
         KANA.add("て"); TRANS.add("t e");
         KANA.add("で"); TRANS.add("d e");
         KANA.add("てぃ"); TRANS.add("t i");
         KANA.add("でぃ"); TRANS.add("d i");
         KANA.add("てゅ"); TRANS.add("t j M");
         KANA.add("てぅ"); TRANS.add("t j M");
         KANA.add("でゅ"); TRANS.add("d j M");
         KANA.add("でぅ"); TRANS.add("d j M");
         KANA.add("と"); TRANS.add("t o");
         KANA.add("ど"); TRANS.add("d o");
         KANA.add("とぅ"); TRANS.add("t M");
         KANA.add("どぅ"); TRANS.add("d M");
         KANA.add("な"); TRANS.add("n a");
         KANA.add("に"); TRANS.add("n j i");
         KANA.add("にぇ"); TRANS.add("n j e");
         KANA.add("にゃ"); TRANS.add("n j a");
         KANA.add("にゅ"); TRANS.add("n j M");
         KANA.add("にょ"); TRANS.add("n j o");
         KANA.add("ぬ"); TRANS.add("n M");
         KANA.add("ね"); TRANS.add("n e");
         KANA.add("の"); TRANS.add("n o");
         KANA.add("は"); TRANS.add("h a");
         KANA.add("ば"); TRANS.add("b a");
         KANA.add("ぱ"); TRANS.add("p a");
         KANA.add("ひ"); TRANS.add("h j i");
         KANA.add("び"); TRANS.add("b j i");
         KANA.add("ぴ"); TRANS.add("p j i");
         KANA.add("ひぇ"); TRANS.add("h j e");
         KANA.add("びぇ"); TRANS.add("b j e");
         KANA.add("ぴぇ"); TRANS.add("p j e");
         KANA.add("ひゃ"); TRANS.add("h j a");
         KANA.add("びゃ"); TRANS.add("b j a");
         KANA.add("ぴゃ"); TRANS.add("p j a");
         KANA.add("ひゅ"); TRANS.add("h j M");
         KANA.add("びゅ"); TRANS.add("b j M");
         KANA.add("ぴゅ"); TRANS.add("p j M");
         KANA.add("ひょ"); TRANS.add("h j o");
         KANA.add("びょ"); TRANS.add("b j o");
         KANA.add("ぴょ"); TRANS.add("p j o");
         KANA.add("ふ"); TRANS.add("p\\ M");
         KANA.add("ぶ"); TRANS.add("b M");
         KANA.add("ぷ"); TRANS.add("p M");
         KANA.add("ふぁ"); TRANS.add("p\\ a");
         KANA.add("ふぃ"); TRANS.add("p\\ j i");
         KANA.add("ふぇ"); TRANS.add("p\\ e");
         KANA.add("ふぉ"); TRANS.add("p\\ o");
         KANA.add("ふゃ"); TRANS.add("p\\ j a");
         KANA.add("ふゅ"); TRANS.add("p\\ j M");
         KANA.add("ふょ"); TRANS.add("p\\ j o");
         KANA.add("へ"); TRANS.add("h e");
         KANA.add("べ"); TRANS.add("b e");
         KANA.add("ぺ"); TRANS.add("p e");
         KANA.add("ほ"); TRANS.add("h o");
         KANA.add("ぼ"); TRANS.add("b o");
         KANA.add("ぽ"); TRANS.add("p o");
         KANA.add("ま"); TRANS.add("m a");
         KANA.add("み"); TRANS.add("m j i");
         KANA.add("みぇ"); TRANS.add("m j e");
         KANA.add("みゃ"); TRANS.add("m j a");
         KANA.add("みゅ"); TRANS.add("m j M");
         KANA.add("みょ"); TRANS.add("m j o");
         KANA.add("む"); TRANS.add("m M");
         KANA.add("め"); TRANS.add("m e");
         KANA.add("も"); TRANS.add("m o");
         KANA.add("や"); TRANS.add("j a");
         KANA.add("ゆ"); TRANS.add("j M");
         KANA.add("よ"); TRANS.add("j o");
         KANA.add("ら"); TRANS.add("4 a");
         KANA.add("り"); TRANS.add("4 i");
         KANA.add("りぇ"); TRANS.add("4 j e");
         KANA.add("りゃ"); TRANS.add("4 j a");
         KANA.add("りゅ"); TRANS.add("4 j M");
         KANA.add("りょ"); TRANS.add("4 j o");
         KANA.add("る"); TRANS.add("4 M");
         KANA.add("れ"); TRANS.add("4 e");
         KANA.add("ろ"); TRANS.add("4 o");
         KANA.add("わ"); TRANS.add("M\\ a");
         KANA.add("ん"); TRANS.add("<N>");
         KANA.add("a"); TRANS.add("<a>"); PRE.add("<a>");
         KANA.add("i"); TRANS.add("<i>"); PRE.add("<i>");
         KANA.add("u"); TRANS.add("<u>"); PRE.add("<u>");
         KANA.add("e"); TRANS.add("<e>"); PRE.add("<e>");
         KANA.add("o"); TRANS.add("<o>"); PRE.add("<o>");
         KANA.add("n"); TRANS.add("<n>"); PRE.add("<n>");
         KANA.add("a息"); TRANS.add("<a> h"); // TRANS.add("-a_^ h");
         KANA.add("i息"); TRANS.add("<i> h"); // TRANS.add("-i_^ h");
         KANA.add("u息"); TRANS.add("<u> h"); // TRANS.add("-M_^ h");
         KANA.add("e息"); TRANS.add("<e> h"); // TRANS.add("-e_^ h");
         KANA.add("o息"); TRANS.add("<o> h"); // TRANS.add("-o_^ h");
         KANA.add("n息"); TRANS.add("<n> h"); // TRANS.add("-N\\=_^ h");
         KANA.add("R"); TRANS.add("(...)");
         KANA.add("r"); TRANS.add("(...)");
         KANA.add("-"); TRANS.add("<->"); PRE.add("<->");
      
         // Convert kana to phonemic transcription
      
         for (int i = 0; i < notes.size(); i++) {
            
            Note currNote = (Note)notes.get(i);
            
            // Check if lyric of note is valid
            if (!currNote.getLyric().equals(Note.LYRIC) &&
                !currNote.getLyric().equals(Note.LYRIC + "R")) {
               
               // Get lyric
               StringTokenizer lyric = new StringTokenizer(currNote.getLyric()
                                                                   .replace(Note.LYRIC, " "));
               String newLyric = "";
               
               // Replace each mora
               int counter = 0;
               while (lyric.hasMoreTokens()) {
                  
                  String mora = lyric.nextToken();
                  int index = KANA.indexOf(mora);
                  
                  if (index == -1 || (counter > 0 && PRE.contains(mora))) {
                     newLyric += "<Q>";
                  }
                  else {
                     newLyric += TRANS.get(index);
                  }
                  
                  if (lyric.hasMoreTokens()) {
                     newLyric += " ";
                  }
                  
                  counter++;
               }
               
               currNote.setLyric(newLyric);
            }
            
            notes.set(i, currNote);
         }
         
         // Add necessary phonemes and convert <N> archiphonemes
         Note currNote,
              prevNote;
         StringTokenizer currLyric,
                         prevLyric;
         ArrayList<String> currPhonemes,
                           prevPhonemes;
         for (int i = notes.size() - 1; i >= 1; i--) {
            
            currNote = (Note)notes.get(i);
            
            if (!currNote.getLyric().equals(Note.LYRIC) &&
                !currNote.getLyric().equals(Note.LYRIC + "R")) {
               
               currLyric = new StringTokenizer(currNote.getLyric()
                                                       .replace(Note.LYRIC, " "));
               
               currPhonemes = new ArrayList<String>(currLyric.countTokens());
               while (currLyric.hasMoreTokens()) {
                  currPhonemes.add(currLyric.nextToken());
               }
               
               int j = i - 1;
               prevNote = (Note)notes.get(j);
               while (j > 0 && (prevNote.getLyric().equals(Note.LYRIC) ||
                                prevNote.getLyric().equals(Note.LYRIC + "R"))) {
                  j--;
                  prevNote = (Note)notes.get(j);
               }
               
               if (j >= 0 && (!prevNote.getLyric().equals(Note.LYRIC) ||
                              !prevNote.getLyric().equals(Note.LYRIC + "R"))) {
                  
                  
                  prevLyric = new StringTokenizer(prevNote.getLyric()
                                                          .replace(Note.LYRIC, " "));
                  
                  prevPhonemes = new ArrayList<String>(prevLyric.countTokens());
                  while (prevLyric.hasMoreTokens()) {
                     prevPhonemes.add(prevLyric.nextToken());
                  }
                  
                  if (currPhonemes.size() >= 2) {
                     int prevNucleusPos = prevPhonemes.size() - 1;
                     String prevNucleus = prevPhonemes.get(prevNucleusPos),
                            currPrefix = currPhonemes.get(0),
                            currOnset = currPhonemes.get(1);
                     
                     if (currPrefix.equals("(...)")) {
                        currPrefix = "<->";
                        currPhonemes.set(0, currPrefix);
                     }
                     
                     if (SEMIVOWELS.contains(currPrefix)) {
                     }
                     else if (currOnset.equals("(...)")) {
                        if (currPrefix.equals("<a>")) {
                           currPhonemes.set(0, "-a_^");
                        }
                        else if (currPrefix.equals("<i>")) {
                           currPhonemes.set(0, "-i_^");
                        }
                        else if (currPrefix.equals("<u>")) {
                           currPhonemes.set(0, "-M_^");
                        }
                        else if (currPrefix.equals("<e>")) {
                           currPhonemes.set(0, "-e_^");
                        }
                        else if (currPrefix.equals("<o>")) {
                           currPhonemes.set(0, "-o_^");
                        }
                        else {
                           currPhonemes.set(0, "-N\\=_^");
                        }
                     }
                     else if (currPrefix.equals("<n>")) {
                        String n_allophone;
                        
                        if (ALVEOLARS.contains(currOnset)) {
                           n_allophone = N_ALVEOLAR;
                        }
                        else if (BILABIALS.contains(currOnset)) {
                           n_allophone = N_BILABIAL;
                        }
                        else if (VELARS.contains(currOnset)) {
                           n_allophone = N_VELAR;
                        }
                        else {
                           /*if (currOnset.equals("d")) {
                              System.out.println("HI");
                           }*/
                           n_allophone = N_OTHER;
                        }
                        
                        if (prevNucleus.equals("<N>")) {
                           prevPhonemes.set(prevNucleusPos, "-" + n_allophone + "=");
                        }
                        else if (VOWELS.contains(prevNucleus)) {
                           prevPhonemes.add(n_allophone);
                        }
                     }
                     else if (currPrefix.equals("<i>") && VOWELS.contains(prevNucleus)
                                                       && ! NASALS.contains(prevNucleus)
                                                       && ! I_VOWELS.contains(prevNucleus)) {
                         prevPhonemes.add("j");
                     }
                     else if (currPrefix.equals("<u>") && VOWELS.contains(prevNucleus)
                                                       && ! NASALS.contains(prevNucleus)
                                                       && ! U_VOWELS.contains(prevNucleus)) {
                         prevPhonemes.add("M\\");
                     }
                     else if ((currPrefix.equals("<a>") || currPrefix.equals("<i>") ||
                               currPrefix.equals("<u>") || currPrefix.equals("<e>") ||
                               currPrefix.equals("<o>")) && (currOnset.equals("a") ||
                               currOnset.equals("i") || currOnset.equals("M") ||
                               currOnset.equals("e") || currOnset.equals("o"))) {
                        currPhonemes.set(1, "-" + currOnset);
                     }
                     else if (currPrefix.equals("<->")) {
                        currPhonemes.remove(0);
                     }
                     /*else if (currPrefix.equals("<a>") && currOnset.equals("aI-")) {
                         currPhonemes.set(1, "-aI-");
                     }
                     else if (currPrefix.equals("<i>") && currOnset.equals("i")) {
                         currPhonemes.set(1, "-i");
                     }
                     else if (currPrefix.equals("<u>") && currOnset.equals("u")) {
                         currPhonemes.set(1, "-u");
                     }
                     else if (currPrefix.equals("<e>") && currOnset.equals("eI-")) {
                         currPhonemes.set(1, "-eI-");
                     }
                     else if (currPrefix.equals("<o>") && currOnset.equals("oU-")) {
                         currPhonemes.set(1, "-oU-");
                     }*/
                  }
                  else {
                     int prevNucleusPos = prevPhonemes.size() - 1;
                     String prevNucleus = prevPhonemes.get(prevNucleusPos),
                            currOnset = currPhonemes.get(0);
                     
                     if (!currOnset.equals("(...)") && !prevNucleus.equals("(...)") &&
                        VOWELS.contains(currOnset)) {
                        currPhonemes.set(0, "-" + currOnset);
                     }
                     else if (currOnset.equals("<->")) {
                        currPhonemes.set(0, "(...)");
                     }
                  }
                        
                  
                  String newPrevLyric = "";
                  for (int k = 0; k < prevPhonemes.size() - 1; k++) {
                     newPrevLyric = newPrevLyric + prevPhonemes.get(k) + " ";
                  }
                  newPrevLyric += prevPhonemes.get(prevPhonemes.size() - 1);
                  
                  prevNote.setLyric(newPrevLyric);
                  notes.set(j, prevNote);
               }
               
               boolean nIsVowel = true;
               for (int k = 0; k < currPhonemes.size(); k++) {
                  
                  String thisPhoneme = currPhonemes.get(k);
                  
                  if (thisPhoneme.equals("<N>")) {
                     if (nIsVowel) {
                        currPhonemes.set(k, "-" + N_OTHER + "=");
                     }
                     else {
                        currPhonemes.set(k, N_OTHER);
                     }
                  }
                  else if (PRE.contains(thisPhoneme)) {
                     currPhonemes.remove(k);
                     k--;
                  }
                  else if (VOWELS.contains(thisPhoneme)) {
                     nIsVowel = false;
                  }
               }
               
               String newCurrLyric = "";
               for (int k = 0; k < currPhonemes.size(); k++) {
                  newCurrLyric += currPhonemes.get(k);
                  if (k < currPhonemes.size() - 1) {
                     newCurrLyric += " ";
                  }
               }
               
               currNote.setLyric(newCurrLyric);
               notes.set(i, currNote);
            }
         }
      }
      
      // Create the new notes
      final int CONS_LENGTH = 20;
      ArrayList<Note> newNotes = new ArrayList<Note>(0);
             
      int counter = 0,
          numOfCons,
          vowelLength,
          noteLength,
          deltaLength = 0,
          deltaDiff = 0;
      
      Note oldNote,
           nextNote;
      
      // String
      
      StringTokenizer lyric;
      ArrayList<String> phonemes;
      String phoneme;
      
      for (int i = 0; i < notes.size(); i++) {
         
         oldNote = (Note)notes.get(i);
         
         // Check if lyric of note is valid
         if (!oldNote.getLyric().equals(Note.LYRIC) &&
             !oldNote.getLyric().equals(Note.LYRIC + "R")) {
             
            // Get lyric
            lyric = new StringTokenizer(oldNote.getLyric()
                                               .replace(Note.LYRIC, " "));
                                               
            // Get phonemes
            phonemes = new ArrayList<String>(lyric.countTokens());
            
            // Figure out how many consonants there are
            numOfCons = 0;
            while (lyric.hasMoreTokens()) {
               
               phoneme = lyric.nextToken();
               phonemes.add(phoneme);
               
               if (CONSONANTS.contains(phoneme)) {
                  numOfCons++;
               }
            }
            
            // Calculate the length of a vowel
            noteLength = new Integer(oldNote.getDuration()
                                            .replaceFirst(Note.DURATION, ""));
            vowelLength = noteLength - numOfCons * CONS_LENGTH;
            
            // Create new notes
            for (int j = 0; j < phonemes.size(); j++) {
               
               currentNote = new Note(oldNote);
               currentNote.setNumber(counter);
               currentNote.setLyric(phonemes.get(j));
               currentNote.unsetPreUtterance();
               
               // If this is the first phoneme
               if (j == 0) {
                  deltaLength = new Integer(oldNote.getDelta()
                                                   .replaceFirst(Note.DELTA, ""));
                  currentNote.setDelta(deltaLength - deltaDiff);
                  deltaDiff = 0;
               }
               else {
                  currentNote.setDelta(deltaLength);
                  currentNote.unsetVoiceOverlap();
                  
                  if (!(VOWELS.contains(phonemes.get(j)) && j != 1)) {
                     currentNote.setPBS("0.0");
                     currentNote.setPBW("0.0,0.0");
                  }
               }
               
               // If this phoneme is a consonant…
               if (CONSONANTS.contains(phonemes.get(j))) {
                  
                  currentNote.setDuration(CONS_LENGTH);
                  currentNote.setLength(CONS_LENGTH);
                  deltaLength = CONS_LENGTH;
                  
                  if (new Float(currentNote.getStartPoint()
                                           .replaceFirst(Note.START_POINT, "")) < -1 * CONS_LENGTH) {
                     currentNote.setStartPoint(-1 * CONS_LENGTH);
                  }
                  
                  // If it isn't the last phoneme
                  if (j != phonemes.size() - 1) {
                     deltaDiff += CONS_LENGTH;
                  }
               }
               // If this phoneme is a vowel…
               else if (VOWELS.contains(phonemes.get(j))) {
                  
                  currentNote.setDuration(vowelLength);
                  currentNote.setLength(vowelLength);
                  deltaLength = vowelLength;
                  
                  // If it is silence
                  if (phonemes.get(j).equals("(...)")) {
                     currentNote.setIntensity(0);
                  }
                  
                  // If it isn't the last phoneme
                  if (j != phonemes.size() - 1) {
                     deltaDiff += vowelLength;
                  }
               }
               
               // Add note to queue
               newNotes.add(currentNote);
               counter++;
            }
         }
         
         else {
            currentNote = new Note(oldNote);
            currentNote.setNumber(counter);
            newNotes.add(currentNote);
            
            deltaLength = new Integer(oldNote.getDelta()
                                      .replaceFirst(Note.DELTA, ""));
            currentNote.setDelta(deltaLength - deltaDiff);
            deltaDiff = 0;
            
            counter++;
         }
      }
      
      // Write new notes to file
      String newFilename = replaceLast(FILENAME, ".ust", " (Parsed).ust");
      PrintWriter outFile = new PrintWriter(newFilename);
      
      outFile.print(fileInfo);
      outFile.print("\r\n");
      for (int i = 0; i < newNotes.size(); i++) {
         outFile.print((Note)newNotes.get(i));
         outFile.print("\r\n");
      }
      outFile.print(LAST_LINE);
      outFile.print("\r\n");
      
      outFile.close();
   }
}
