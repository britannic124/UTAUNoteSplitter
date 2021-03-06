// Import classes
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
   Represents a note in an UTAU file.
   @author J.P. O'Malley
*/

public class Note {
   
   // Fields
   
   // Class constants
   public final static String DELTA = "Delta=",
                              DURATION = "Duration=",
                              LENGTH = "Length=",
                              LYRIC = "Lyric=",
                              TEMPO = "Tempo=",
                              NOTE_NUM = "NoteNum=",
                              PRE_UTTERANCE = "PreUtterance=",
                              VOICE_OVERLAP = "VoiceOverlap=",
                              VELOCITY = "Velocity=",
                              START_POINT = "StartPoint=",
                              INTENSITY = "Intensity=",
                              MODULATION = "Modulation=",
                              FLAGS = "Flags=",
                              PBS = "PBS=",
                              PBW = "PBW=",
                              PBY = "PBY=",
                              ENVELOPE = "Envelope=",
                              VBR = "VBR=";
   
   public final static DecimalFormat NUMBER = new DecimalFormat("0000"),
                                     TENTH = new DecimalFormat("0.0"),
                                     HUNDREDTH = new DecimalFormat("0.00");
   
   // Declare variables
   
   /** The position of this note. */
   private Integer number;
   /** The distance this note is from previous note. */
   private Integer delta;
   /** The duration of this note. */
   private Integer duration;
   /** The length of this note. */
   private Integer length;
   /** The lyric of this note. */
   private String lyric;
   /** The tempo at this note. */
   private Double tempo;
   /** The pitch of this note. */
   private Integer noteNum;
   /** The pre-utterance of this note. */
   private Double preUtterance;
   /** The voice overlap of this note. */
   private Double voiceOverlap;
   /** The velocity of this note. */
   private Double velocity;
   /** The starting point of this note. */
   private Double startPoint;
   /** The intensity of this note. */
   private Integer intensity;
   /** The modulation of this note. */
   private Integer modulation;
   /** The flags of this note. */
   private String flags;
   /** The pitch bend sensitivity of this note. */
   private ArrayList<Double> pbs;
   private ArrayList<Double> pbw;
   private ArrayList<Double> pby;
   /** The envelope of this note. */
   private ArrayList<Double> envelope;
   /** The vibrato of this note. */
   private ArrayList<Integer> vbr;
   
   
   // Methods
   
   
   // Constructors
   
   /**
      Creates a note with the position given.
      @param number the position of this note.
   */
   public Note(int number) {
      setNumber(number);
   }
   
   /**
      Creates a note with the position given.
      @param number the number parameter for this note.
   */
   public Note(String number) {
      setNumber(number);
   }
   
   /**
      Creates a copy of the given note.
      @param copy the note to make a copy of.
   */
   public Note(Note copy) {
      
      setNumber(copy.getNumber());
      if (copy.getDelta() != null) {
         setDelta(copy.getDelta());
      }
      if (copy.getDuration() != null) {
         setDuration(copy.getDuration());
      }
      if (copy.getLength() != null) {
         setLength(copy.getLength());
      }
      if (copy.getLyric() != null) {
         setLyric(copy.getLyric());
      }
      if (copy.getNoteNum() != null) {
         setNoteNum(copy.getNoteNum());
      }
      if (copy.getPreUtterance() != null) {
         setPreUtterance(copy.getPreUtterance());
      }
      if (copy.getVoiceOverlap() != null) {
         setVoiceOverlap(copy.getVoiceOverlap());
      }
      if (copy.getVelocity() != null) {
         setVelocity(copy.getVelocity());
      }
      if (copy.getStartPoint() != null) {
         setStartPoint(copy.getStartPoint());
      }
      if (copy.getIntensity() != null) {
         setIntensity(copy.getIntensity());
      }
      if (copy.getModulation() != null) {
         setModulation(copy.getModulation());
      }
      if (copy.getFlags() != null) {
         setFlags(copy.getFlags());
      }
      if (copy.getPBS() != null) {
         setPBS(copy.getPBS());
      }
      if (copy.getPBW() != null) {
         setPBW(copy.getPBW());
      }
      if (copy.getPBY() != null) {
         setPBY(copy.getPBY());
      }
      if (copy.getEnvelope() != null) {
         setEnvelope(copy.getEnvelope());
      }
      if (copy.getVBR() != null) {
         setVBR(copy.getVBR());
      }
   }
   
   
   // Setters
   
   
   // Number
   
   /**
      Sets the value of the {@link #number} field of this note.
      @param number the position of this note.
   */
   public void setNumber(int number) {
      this.number = new Integer(number);
   }
   
   /**
      Sets the value of the {@link #number} field of this note.
      @param number the number parameter for this note.
   */
   public void setNumber(String number) {
      
      // Remove non-numerical characters and set value
      this.number = new Integer(number.replaceAll("[^0-9]", ""));
   }
   
   // Delta
   
   /**
      Sets the value of the {@link #delta} field of this note.
      @param delta the distance this note is from the previous note.
   */
   public void setDelta(int delta) {
      
      this.delta = new Integer(delta);
   }
   
   /**
      Sets the value of the {@link #delta} field of this note.
      @param delta the Delta parameter for this note.
   */
   public void setDelta(String delta) {
      
      // Remove prefix and set value
      this.delta = new Integer(delta.replaceFirst(DELTA, ""));
   }
   
   // Duration
   
   /**
      Sets the value of the {@link #duration} field of this note.
      @param duration the duration of this note.
   */
   public void setDuration(int duration) {
      
      this.duration = new Integer(duration);
   }
   
   /**
      Sets the value of the {@link #duration} field of this note.
      @param duration the Duration parameter for this note.
   */
   public void setDuration(String duration) {
      
      // Remove prefix and set value
      this.duration = new Integer(duration.replaceFirst(DURATION, ""));
   }
   
   // Length
   
   /**
      Sets the value of the {@link #length} field of this note.
      @param length the length of this note.
   */
   public void setLength(int length) {
      
      this.length = new Integer(length);
   }
   
   /**
      Sets the value of the {@link #length} field of this note.
      @param length the Length parameter for this note.
   */
   public void setLength(String length) {
      
      // Remove prefix and set value
      this.length = new Integer(length.replaceFirst(LENGTH, ""));
   }
   
   // Lyric
   
   /**
      Sets the value of the {@link #lyric} field of this note.
      @param lyric the lyric of or the Lyric parameter for this note.
   */
   public void setLyric(String lyric) {
      
      // Remove prefix and set value
      this.lyric = lyric.replaceFirst(LYRIC, "");
   }
   
   // Tempo
   
   /**
      Sets the value of the {@link #tempo} field of this note.
      @param tempo the tempo of this note.
   */
   public void setTempo(double tempo) {
      
      this.tempo = new Double(tempo);
   }
   
   /**
      Sets the value of the {@link #tempo} field of this note.
      @param tempo the Tempo parameter for this note.
   */
   public void setTempo(String tempo) {
      
      // Remove prefix and set value
      this.tempo = new Double(tempo.replaceFirst(TEMPO, ""));
   }
   
   // NoteNum
   
   /**
      Sets the value of the {@link #noteNum} field of this note.
      @param noteNum the pitch of this note.
   */
   public void setNoteNum(int noteNum) {
      
      this.noteNum = new Integer(noteNum);
   }
   
   /**
      Sets the value of the {@link #noteNum} field of this note.
      @param noteNum the NoteNum parameter for this note.
   */
   public void setNoteNum(String noteNum) {
      
      // Remove prefix and set value
      this.noteNum = new Integer(noteNum.replaceFirst(NOTE_NUM, ""));
   }
   
   // Pre-utterance
   
   /**
      Sets the value of the {@link #preUtterance} field of this note.
      @param preUtterance the preUtterance of this note.
   */
   public void setPreUtterance(double preUtterance) {
      
      this.preUtterance = new Double(preUtterance);
   }
   
   /**
      Sets the value of the {@link #preUtterance} field of this note.
      @param preUtterance the PreUtterance parameter for this note.
   */
   public void setPreUtterance(String preUtterance) {
      
      // Remove prefix and set value
      this.preUtterance = new Double(preUtterance.replaceFirst(PRE_UTTERANCE, ""));
   }
   
   /**
      Sets the value of the {@link #preUtterance} field of this note to null.
   */
   public void unsetPreUtterance() {
      
      preUtterance = null;
   }
   
   // Voice overlap
   
   /**
      Sets the value of the {@link #voiceOverlap} field of this note.
      @param voiceOverlap the voiceOverlap of this note.
   */
   public void setVoiceOverlap(double voiceOverlap) {
      
      this.voiceOverlap = new Double(voiceOverlap);
   }
   
   /**
      Sets the value of the {@link #voiceOverlap} field of this note.
      @param voiceOverlap the VoiceOverlap parameter for this note.
   */
   public void setVoiceOverlap(String voiceOverlap) {
      
      // Remove prefix and set value
      this.voiceOverlap = new Double(voiceOverlap.replaceFirst(VOICE_OVERLAP, ""));
   }
   
   /**
      Sets the value of the {@link #voiceOverlap} field of this note to null.
   */
   public void unsetVoiceOverlap() {
      
      voiceOverlap = null;
   }
   
   // Velocity
   
   /**
      Sets the value of the {@link #velocity} field of this note.
      @param velocity the velocity of this note.
   */
   public void setVelocity(double velocity) {
      
      this.velocity = new Double(velocity);
   }
   
   /**
      Sets the value of the {@link #velocity} field of this note.
      @param velocity the Velocity parameter for this note.
   */
   public void setVelocity(String velocity) {
      
      // Remove prefix and set value
      this.velocity = new Double(velocity.replaceFirst(VELOCITY, ""));
   }
   
   // StartPoint
   
   /**
      Sets the value of the {@link #startPoint} field of this note.
      @param startPoint the starting point of this note.
   */
   public void setStartPoint(double startPoint) {
      
      this.startPoint = new Double(startPoint);
   }
   
   /**
      Sets the value of the {@link #startPoint} field of this note.
      @param startPoint the StartPoint parameter for this note.
   */
   public void setStartPoint(String startPoint) {
      
      // Remove prefix and set value
      this.startPoint = new Double(startPoint.replaceFirst(START_POINT, ""));
   }
   
   // Intensity
   
   /**
      Sets the value of the {@link #intensity} field of this note.
      @param intensity of this note.
   */
   public void setIntensity(int intensity) {
      
      this.intensity = new Integer(intensity);
   }
   
   /**
      Sets the value of the {@link #intensity} field of this note.
      @param intensity the Intensity parameter for this note.
   */
   public void setIntensity(String intensity) {
      
      // Remove prefix and set value
      this.intensity = new Integer(intensity.replaceFirst(INTENSITY, ""));
   }

   // Modulation
   
   /**
      Sets the value of the {@link #modulation} field of this note.
      @param modulation of this note.
   */
   public void setModulation(int modulation) {
      
      this.modulation = new Integer(modulation);
   }
   
   /**
      Sets the value of the {@link #modulation} field of this note.
      @param modulation the Modulation parameter for this note.
   */
   public void setModulation(String modulation) {
      
      // Remove prefix and set value
      this.modulation = new Integer(modulation.replaceFirst(MODULATION, ""));
   }
   
   // Flags
   
   /**
      Sets the value of the {@link #flags} field of this note.
      @param flags the flags of or the Flags parameter for this note.
   */
   public void setFlags(String flags) {
      
      // Remove prefix and set value
      this.flags = flags.replaceFirst(FLAGS, "");
   }
   
   // PBS
   
   /**
      Sets the value of the {@link #pbs} field of this note.
      @param pbs the pitch bend sensitivity of this note.
   */
   public void setPBS(ArrayList<Double> pbs) {
      
      // Create an array list of the appropriate size
      this.pbs = new ArrayList<Double>(pbs);
   }
   
   /**
      Sets the value of the {@link #pbs} field for this note.
      @param pbs the PBS for this note.
   */
   public void setPBS(String pbs) {
      
      // Create a string tokenizer
      StringTokenizer str = new StringTokenizer(
                                   // Remove parameter prefix
                                   pbs.replaceFirst(PBS, ""),
                                   ",");
      
      // Create an array list of the needed size
      this.pbs = new ArrayList<Double>(str.countTokens());
      
      // Add each value to the field
      while (str.hasMoreTokens()) {
         this.pbs.add(new Double(str.nextToken()));
      }
   }
   
   // PBW
   
   /**
      Sets the value of the {@link #pbw} field of this note.
      @param pbw the PBW of this note.
   */
   public void setPBW(ArrayList<Double> pbw) {
      
      // Create an array list of the appropriate size
      this.pbw = new ArrayList<Double>(pbw);
   }
   
   /**
      Sets the value of the {@link #pbw} field for this note.
      @param pbw the PBW parameter for this note.
   */
   public void setPBW(String pbw) {
      
      // Create a string tokenizer
      StringTokenizer str = new StringTokenizer(
                                   // Remove parameter prefix
                                   pbw.replaceFirst(PBW, ""),
                                   ",");
      
      // Create an array list of the needed size
      this.pbw = new ArrayList<Double>(str.countTokens());
      
      // Add each value to the field
      while (str.hasMoreTokens()) {
         this.pbw.add(new Double(str.nextToken()));
      }
   }
   
   // PBY
   
   /**
      Sets the value of the {@link #pby} field of this note.
      @param pby the pitch bend sensitivity of this note.
   */
   public void setPBY(ArrayList<Double> pby) {
      
      // Create an array list of the appropriate size
      this.pby = new ArrayList<Double>(pby);
   }
   
   /**
      Sets the value of the {@link #pby} field for this note.
      @param pby the PBY parameter for this note.
   */
   public void setPBY(String pby) {
      
      // Create a string tokenizer
      StringTokenizer str = new StringTokenizer(
                                   // Remove parameter prefix
                                   pby.replaceFirst(PBY, " "),
                                   ",");
      
      // Create an array list of the needed size
      this.pby = new ArrayList<Double>(str.countTokens());
      
      // Add each value to the field
      while (str.hasMoreTokens()) {
         this.pby.add(new Double(str.nextToken()));
      }
   }
   
   // Envelope
   
   /**
      Sets the value of the {@link #envelope} field of this note.
      @param envelope the envelope of this note.
   */
   public void setEnvelope(ArrayList<Double> envelope) {
      
      // Create an array list of the appropriate size
      this.envelope = new ArrayList<Double>(envelope);
   }
   
   /**
      Sets the value of the {@link #envelope} field for this note.
      @param envelope the Envelope parameter for this note.
   */
   public void setEnvelope(String envelope) {
      
      // Create a string tokenizer
      StringTokenizer str = new StringTokenizer(
                                   // Remove parameter prefix
                                   envelope.replaceFirst(ENVELOPE, ""),
                                   ",");
      
      // Create an array list of the needed size
      this.envelope = new ArrayList<Double>(str.countTokens());
      
      // Add each value to the field
      while (str.hasMoreTokens()) {
         this.envelope.add(new Double(str.nextToken()));
      }
   }
   
   // VBR
   
   /**
      Sets the value of the {@link #vbr} field of this note.
      @param vbr the pitch bend sensitivity of this note.
   */
   public void setVBR(ArrayList<Integer> vbr) {
      
      // Create an array list of the appropriate size
      this.vbr = new ArrayList<Integer>(vbr);
   }
   
   /**
      Sets the value of the {@link #vbr} field for this note.
      @param vbr the VBR parameter for this note.
   */
   public void setVBR(String vbr) {
      
      // Create a string tokenizer
      StringTokenizer str = new StringTokenizer(
                                   // Remove parameter prefix
                                   vbr.replaceFirst(VBR, ""),
                                   ","),
                        sjt = new StringTokenizer("dsoi");
      
      // Create an array list of the needed size
      this.vbr = new ArrayList<Integer>(str.countTokens());
      
      // Add each value to the field
      while (str.hasMoreTokens()) {
         this.vbr.add(new Integer(str.nextToken()));
      }
   }
   
   
   // Getters
   
   
   // Number
   
   /**
      @return the number parameter for this note.
   */
   public String getNumber() {
      return "[#" + NUMBER.format(number) + "]";
   }
   
   // Delta
   
   /**
      @return the Delta parameter for this note.
   */
   public String getDelta() {
      
      if (delta != null) {
         return DELTA + delta;
      }
      else {
         return null;
      }
   }
   
   // Duration
   
   /**
      @return the Duration parameter for this note.
   */
   public String getDuration() {
      
      if (duration != null) {
         return DURATION + duration;
      }
      else {
         return null;
      }
   }
   
   // Length
   
   /**
      @return the Length parameter for this note.
   */
   public String getLength() {
      
      if (length != null) {
         return LENGTH + length;
      }
      else {
         return null;
      }
   }
   
   // Lyric
   
   /**
      @return the Lyric parameter for this note.
   */
   public String getLyric() {
      
      if (lyric != null) {
         return LYRIC + lyric;
      }
      else {
         return null;
      }
   }
   
   // NoteNum
   
   /**
      @return the NoteNum parameter for this note.
   */
   public String getNoteNum() {
      
      if (noteNum != null) {
         return NOTE_NUM + noteNum;
      }
      else {
         return null;
      }
   }
   
   // Pre-utterance
   
   /**
      @return the PreUtterance parameter for this note.
   */
   public String getPreUtterance() {
      
      if (preUtterance != null) {
         return PRE_UTTERANCE + HUNDREDTH.format(preUtterance);
      }
      else {
         return null;
      }
   }
   
   // Voice overlap
   
   /**
      @return the voice overlap parameter for this note.
   */
   public String getVoiceOverlap() {
      
      if (voiceOverlap != null) {
         return VOICE_OVERLAP + HUNDREDTH.format(voiceOverlap);
      }
      else {
         return null;
      }
   }
   
   // Velocity
   
   /**
      @return the Velocity parameter for this note.
   */
   public String getVelocity() {
      
      if (velocity != null) {
         return VELOCITY + HUNDREDTH.format(velocity);
      }
      else {
         return null;
      }
   }
   
   // StartPoint
   
   /**
      @return the StartPoint parameter for this note.
   */
   public String getStartPoint() {
      
      if (startPoint != null) {
         return START_POINT + HUNDREDTH.format(startPoint);
      }
      else {
         return null;
      }
   }
   
   // Intensity
   
   /**
      @return the Intensity parameter for this note.
   */
   public String getIntensity() {
      
      if (intensity != null) {
         return INTENSITY + intensity;
      }
      else {
         return null;
      }
   }
   
   // Modulation
   
   /**
      @return the Modulation parameter for this note.
   */
   public String getModulation() {
      
      if (modulation != null) {
         return MODULATION + modulation;
      }
      else {
         return null;
      }
   }
   
   // Flags
   
   /**
      @return the Flags parameter for this note.
   */
   public String getFlags() {
      
      if (flags != null) {
         return FLAGS + flags;
      }
      else {
         return null;
      }
   }
   
   // PBS
   
   /**
      @return the PBS parameter for this note.
   */
   public String getPBS() {
      
      if (pbs != null) {
         
         String str = PBS; // Add parameter prefix
         
         for (int i = 0; i < pbs.size(); i++) {
            
            // Add each number with one decimal to the string
            str += TENTH.format(pbs.get(i));
            
            // If it's not the last number, add a comma
            if (i != pbs.size() - 1) {
               str += ",";
            }
         }
         
         return str;
      }
      
      else {
         return null;
      }
   }
   
   // PBW
   
   /**
      @return the PBW parameter for this note.
   */
   public String getPBW() {
      
      if (pbw != null) {
         
         String str = PBW; // Add parameter prefix
         
         for (int i = 0; i < pbw.size(); i++) {
            
            // Add each number with one decimal to the string
            str += TENTH.format(pbw.get(i));
            
            // If it's not the last number, add a comma
            if (i != pbw.size() - 1) {
               str += ",";
            }
         }
         
         return str;
      }
      
      else {
         return null;
      }
   }
   
   // PBY
   
   /**
      @return the PBY parameter for this note.
   */
   public String getPBY() {
      
      if (pby != null) {
         
         String str = PBY; // Add parameter prefix
         
         for (int i = 0; i < pby.size(); i++) {
            
            // Add each number with one decimal to the string
            str += TENTH.format(pby.get(i));
            
            // If it's not the last number, add a comma
            if (i != pby.size() - 1) {
               str += ",";
            }
         }
         
         return str;
      }
      
      else {
         return null;
      }
   }
   
   // Envelope
   
   /**
      @return the Envelope parameter for this note.
   */
   public String getEnvelope() {
      
      if (envelope != null) {
         
         String str = ENVELOPE; // Add parameter prefix
         
         for (int i = 0; i < envelope.size(); i++) {
            
            // Add each number with one decimal to the string
            str += TENTH.format(envelope.get(i));
            
            // If it's not the last number, add a comma
            if (i != envelope.size() - 1) {
               str += ",";
            }
         }
         
         return str;
      }
      
      else {
         return null;
      }
   }
   
   // VBR
   
   /**
      @return the VBR parameter for this note.
   */
   public String getVBR() {
      
      if (vbr != null) {
         
         String str = VBR; // Add parameter prefix
         
         for (int i = 0; i < vbr.size(); i++) {
            
            // Add each number with one decimal to the string
            str += vbr.get(i);
            
            // If it's not the last number, add a comma
            if (i != vbr.size() - 1) {
               str += ",";
            }
         }
         
         return str;
      }
      
      else {
         return null;
      }
   }
   
   
   // To string
   public String toString() {
      
      String str = getNumber();
      
      if (getDelta() != null) {
         str += "\r\n" + getDelta();
      }
      if (getDuration() != null) {
         str += "\r\n" + getDuration();
      }
      if (getLength() != null) {
         str += "\r\n" + getLength();
      }
      if (getLyric() != null) {
         str += "\r\n" + getLyric();
      }
      if (getNoteNum() != null) {
         str += "\r\n" + getNoteNum();
      }
      if (getPreUtterance() != null) {
         str += "\r\n" + getPreUtterance();
      }
      if (getVoiceOverlap() != null) {
         str += "\r\n" + getVoiceOverlap();
      }
      if (getVelocity() != null) {
         str += "\r\n" + getVelocity();
      }
      if (getStartPoint() != null) {
         str += "\r\n" + getStartPoint();
      }
      if (getIntensity() != null) {
         str += "\r\n" + getIntensity();
      }
      if (getModulation() != null) {
         str += "\r\n" + getModulation();
      }
      if (getFlags() != null) {
         str += "\r\n" + getFlags();
      }
      if (getPBS() != null) {
         str += "\r\n" + getPBS();
      }
      if (getPBW() != null) {
         str += "\r\n" + getPBW();
      }
      if (getPBY() != null) {
         str += "\r\n" + getPBY();
      }
      if (getEnvelope() != null) {
         str += "\r\n" + getEnvelope();
      }
      if (getVBR() != null) {
         str += "\r\n" + getVBR();
      }
      
      return str;
   }
}
