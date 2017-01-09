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
   
   public static void main(String[] args) throws FileNotFoundException {
      
      // Declare constants
      String FIRST_NOTE = "[#0000]",
             FIRST_LINE = "[#VERSION]",
             LAST_LINE = "[#TRACKEND]",
             FILENAME = args[0];
      
      
      // Open the file
      File inFile = new File(FILENAME);
      Scanner file = new Scanner(inFile);
      file.useDelimiter("[\r\n]");
      
      String fileInfo = "";
      
      // Read the file
      String line = "";
      
      while (file.hasNext() && !(line.equals(FIRST_NOTE))) {
         
         line = file.next();
         
         if (!(line.equals(FIRST_NOTE))) {
            if (!(line.equals(FIRST_LINE))) {
               fileInfo += "\n";
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
      
      
      // Create the new notes
      final int CONS_LENGTH = 10;
      ArrayList<Note> newNotes = new ArrayList<Note>(0);
      
      final ArrayList<String> PHONEMES = new ArrayList<String>();
      final ArrayList<String> CONSONANTS = new ArrayList<String>();
      final ArrayList<String> VOWELS = new ArrayList<String>();
      
      CONSONANTS.add("4");
      CONSONANTS.add("5");
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
      CONSONANTS.add("m");
      CONSONANTS.add("N");
      CONSONANTS.add("n");
      CONSONANTS.add("p");
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
      CONSONANTS.add("Z");
      CONSONANTS.add("z");
      CONSONANTS.add("*?");
      
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
      VOWELS.add("O");
      VOWELS.add("Or\\");
      VOWELS.add("U");
      VOWELS.add("U\\");
      VOWELS.add("Ur\\");
      VOWELS.add("aI");
      VOWELS.add("aIr\\");
      VOWELS.add("aU");
      VOWELS.add("eI");
      VOWELS.add("i");
      VOWELS.add("oU");
      VOWELS.add("u");
      
      int origSize = VOWELS.size();
      String vowel;
      
      for (int i = 0; i < origSize; i++) {
         
         vowel = VOWELS.get(i);
         
         VOWELS.add("-" + vowel);
         VOWELS.add(vowel + "-");
         VOWELS.add("-" + vowel + "-");
      }
      
      PHONEMES.addAll(CONSONANTS);
      PHONEMES.addAll(VOWELS);
             
      int counter = 0,
          numOfCons,
          vowelLength,
          noteLength,
          deltaLength = 0,
          deltaDiff = 0;
      
      Note oldNote,
           prevNote;
      
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
               
               // If this is the first phoneme
               if (j == 0) {
                  deltaLength = new Integer(oldNote.getDelta()
                                            .replaceFirst(Note.DELTA, ""));
                  currentNote.setDelta(deltaLength - deltaDiff);
                  deltaDiff = 0;
               }
               else {
                  currentNote.setDelta(deltaLength);
                  currentNote.setPBS("0.0");
                  currentNote.setPBW("0.0,0.0");
               }
               
               // If this phoneme is a consonant…
               if (CONSONANTS.contains(phonemes.get(j))) {
                  
                  currentNote.setDuration(CONS_LENGTH);
                  currentNote.setLength(CONS_LENGTH);
                  deltaLength = CONS_LENGTH;
                  
                  // If it isn't the last phoneme
                  if (j != phonemes.size() - 1) {
                     deltaDiff += CONS_LENGTH;
                  }
               }
               // If this phoneme is a vowel…
               else {
                  
                  currentNote.setDuration(vowelLength);
                  currentNote.setLength(vowelLength);
                  deltaLength = vowelLength;
                  
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
            deltaDiff = 0;
            counter++;
         }
      }
      
      // Write new notes to file
      String newFilename = FILENAME.replaceFirst(".ust", "") + " (Compiled).ust";
      PrintWriter outFile = new PrintWriter(newFilename);
      
      outFile.println(fileInfo);
      for (int i = 0; i < newNotes.size(); i++) {
         outFile.println((Note)newNotes.get(i));
      }
      outFile.println(LAST_LINE);
      
      outFile.close();
   }
}