package org.dogtrainingapp.dog_training_app.model;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DogManagementTest {

    private DogManagement mgmt;
    private TherapyDog izzy;
    private PoliceDog chase;
    private RescueDog fin;

    /**
     * Runs before EACH test.
     * Creates a fresh DogManagement and sample dogs.
     */
    @BeforeEach
    void setUp() {
        mgmt = new DogManagement();
        izzy = new TherapyDog(1, "Izzy", 3, "Golden Retriever");
        chase = new PoliceDog(2, "Chase", 5, "German Shepherd");
        fin = new RescueDog(3, "Fin", 4, "Labrador");
    }

    /**
     * Runs after EACH test.
     * Cleans up any test files created during file I/O tests.
     */
    @AfterEach
    void tearDown() throws IOException {
        Path dogFile    = Paths.get("data", "dogs.txt");
        Path recordFile = Paths.get("data", "records.txt");
        Files.deleteIfExists(dogFile);
        Files.deleteIfExists(recordFile);
    }

    @Test
    @DisplayName("addDog — adds a single dog to the list")
    void testAddDog_Single() {
        mgmt.addDog(izzy);
        assertEquals(1, mgmt.getDogs().size());
        assertEquals("Izzy", mgmt.getDogs().get(0).getName());
    }

    @Test
    @DisplayName("addDog — adds multiple dogs to the list")
    void testAddDog_Multiple() {
        mgmt.addDog(izzy);
        mgmt.addDog(chase);
        mgmt.addDog(fin);
        assertEquals(3, mgmt.getDogs().size());
    }

    @Test
    @DisplayName("getDogs — returns empty list when no dogs added")
    void testGetDogs_Empty() {
        assertTrue(mgmt.getDogs().isEmpty());
    }

    @Test
    @DisplayName("searchByID — returns correct dog when found")
    void testSearchByID_Found() {
        mgmt.addDog(izzy);
        mgmt.addDog(chase);

        Dog result = mgmt.searchByID(1);
        assertNotNull(result);
        assertEquals("Izzy", result.getName());
        assertEquals(1, result.getDogID());
    }

    @Test
    @DisplayName("searchByID — returns null when not found")
    void testSearchByID_NotFound() {
        mgmt.addDog(izzy);
        assertNull(mgmt.searchByID(999));
    }

    @Test
    @DisplayName("searchByID — returns null when list is empty")
    void testSearchByID_EmptyList() {
        assertNull(mgmt.searchByID(1));
    }

    @Test
    @DisplayName("searchByName — returns matching dogs (case-insensitive)")
    void testSearchByName_Found() {
        mgmt.addDog(izzy);
        mgmt.addDog(chase);

        List<Dog> results = mgmt.searchByName("izzy");   // lowercase
        assertEquals(1, results.size());
        assertEquals("Izzy", results.get(0).getName());
    }

    @Test
    @DisplayName("searchByName — returns empty list when no match")
    void testSearchByName_NotFound() {
        mgmt.addDog(izzy);
        List<Dog> results = mgmt.searchByName("Charlie");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("searchByName — returns multiple dogs with same name")
    void testSearchByName_MultipleSameName() {
        TherapyDog bella2 = new TherapyDog(4, "Izzy", 2, "Poodle");
        mgmt.addDog(izzy);
        mgmt.addDog(bella2);

        List<Dog> results = mgmt.searchByName("Izzy");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("searchByType — returns all dogs of a given type")
    void testSearchByType_Found() {
        mgmt.addDog(izzy);
        mgmt.addDog(chase);
        mgmt.addDog(fin);

        List<Dog> therapyDogs = mgmt.searchByType(DogType.THERAPY);
        assertEquals(1, therapyDogs.size());
        assertEquals(DogType.THERAPY, therapyDogs.get(0).getType());
    }

    @Test
    @DisplayName("searchByType — returns empty list when no match")
    void testSearchByType_NotFound() {
        mgmt.addDog(izzy);
        List<Dog> policeDogs = mgmt.searchByType(DogType.POLICE);
        assertTrue(policeDogs.isEmpty());
    }

    @Test
    @DisplayName("searchByType — returns multiple dogs of same type")
    void testSearchByType_Multiple() {
        PoliceDog k9 = new PoliceDog(4, "K9", 3, "Belgian Malinois");
        mgmt.addDog(chase);
        mgmt.addDog(k9);

        List<Dog> policeDogs = mgmt.searchByType(DogType.POLICE);
        assertEquals(2, policeDogs.size());
    }

    @Test
    @DisplayName("speak — TherapyDog returns comfort message")
    void testSpeak_TherapyDog() {
        String result = izzy.speak();
        assertNotNull(result);
        assertTrue(result.contains("Izzy"));
        assertTrue(result.contains("comfort"));
    }

    @Test
    @DisplayName("speak — PoliceDog returns halt message")
    void testSpeak_PoliceDog() {
        String result = chase.speak();
        assertNotNull(result);
        assertTrue(result.contains("Chase"));
        assertTrue(result.contains("Halt"));
    }

    @Test
    @DisplayName("speak — RescueDog returns found message")
    void testSpeak_RescueDog() {
        String result = fin.speak();
        assertNotNull(result);
        assertTrue(result.contains("Fin"));
        assertTrue(result.contains("found"));
    }

    @Test
    @DisplayName("doTrick — each dog type returns a unique trick")
    void testDoTrick_AllTypes() {
        assertNotNull(izzy.doTrick());
        assertNotNull(chase.doTrick());
        assertNotNull(fin.doTrick());
        // Verify tricks are different
        assertNotEquals(izzy.doTrick(), chase.doTrick());
        assertNotEquals(chase.doTrick(), fin.doTrick());
    }

    @Test
    @DisplayName("getType — returns correct DogType for each subclass")
    void testGetType() {
        assertEquals(DogType.THERAPY, izzy.getType());
        assertEquals(DogType.POLICE, chase.getType());
        assertEquals(DogType.RESCUE, fin.getType());
    }

    @Test
    @DisplayName("addRecord — dog starts with empty records")
    void testRecords_InitiallyEmpty() {
        assertTrue(izzy.getRecords().isEmpty());
    }

    @Test
    @DisplayName("addRecord — adds a record to the dog")
    void testAddRecord_Single() {
        TrainingRecord record = new TrainingRecord("Sit", "2026-03-15", Performance.EXCELLENT);
        izzy.addRecord(record);

        assertEquals(1, izzy.getRecords().size());
        assertEquals("Sit", izzy.getRecords().get(0).getSkill());
        assertEquals("2026-03-15", izzy.getRecords().get(0).getDate());
        assertEquals(Performance.EXCELLENT, izzy.getRecords().get(0).getPerformance());
    }

    @Test
    @DisplayName("addRecord — adds multiple records to the dog")
    void testAddRecord_Multiple() {
        izzy.addRecord(new TrainingRecord("Sit", "2026-03-15", Performance.EXCELLENT));
        izzy.addRecord(new TrainingRecord("Stay", "2026-03-20", Performance.GOOD));
        izzy.addRecord(new TrainingRecord("Paw", "2026-04-01", Performance.SATISFACTORY));

        assertEquals(3, izzy.getRecords().size());
    }

    @Test
    @DisplayName("DogType — name() returns constant name")
    void testDogType_Name() {
        assertEquals("THERAPY", DogType.THERAPY.name());
        assertEquals("POLICE", DogType.POLICE.name());
        assertEquals("RESCUE", DogType.RESCUE.name());
    }

    @Test
    @DisplayName("DogType — valueOf() converts string to enum")
    void testDogType_ValueOf() {
        assertEquals(DogType.THERAPY, DogType.valueOf("THERAPY"));
        assertEquals(DogType.POLICE, DogType.valueOf("POLICE"));
        assertEquals(DogType.RESCUE, DogType.valueOf("RESCUE"));
    }

    @Test
    @DisplayName("DogType — getLabel() returns friendly label")
    void testDogType_GetLabel() {
        assertEquals("Therapy dog", DogType.THERAPY.getLabel());
        assertEquals("Police dog", DogType.POLICE.getLabel());
        assertEquals("Rescue dog", DogType.RESCUE.getLabel());
    }

    @Test
    @DisplayName("DogType — values() returns all three types")
    void testDogType_Values() {
        DogType[] types = DogType.values();
        assertEquals(3, types.length);
    }

    @Test
    @DisplayName("Performance — name() and getLabel() differ correctly")
    void testPerformance_NameVsLabel() {
        assertEquals("NEEDS_IMPROV", Performance.NEEDS_IMPROV.name());
        assertEquals("Needs Improvement", Performance.NEEDS_IMPROV.getLabel());
    }

    @Test
    @DisplayName("Performance — valueOf() round-trips correctly")
    void testPerformance_ValueOf() {
        for (Performance p : Performance.values()) {
            assertEquals(p, Performance.valueOf(p.name()));
        }
    }

    @Test
    @DisplayName("saveDogs/loadDogs — data survives save and reload")
    void testSaveAndLoadDogs() {
        mgmt.addDog(izzy);
        mgmt.addDog(chase);
        mgmt.addDog(fin);
        mgmt.saveDogs();

        // Create a NEW DogManagement and load from file
        DogManagement mgmt2 = new DogManagement();
        mgmt2.loadDogs();

        assertEquals(3, mgmt2.getDogs().size());

        Dog loadedBella = mgmt2.searchByID(1);
        assertNotNull(loadedBella);
        assertEquals("Izzy", loadedBella.getName());
        assertEquals(3, loadedBella.getAge());
        assertEquals("Golden Retriever", loadedBella.getBreed());
        assertEquals(DogType.THERAPY, loadedBella.getType());

        Dog loadedRex = mgmt2.searchByID(2);
        assertNotNull(loadedRex);
        assertEquals(DogType.POLICE, loadedRex.getType());

        Dog loadedMax = mgmt2.searchByID(3);
        assertNotNull(loadedMax);
        assertEquals(DogType.RESCUE, loadedMax.getType());
    }

    @Test
    @DisplayName("loadDogs — returns empty list when file does not exist")
    void testLoadDogs_FileNotFound() {
        mgmt.loadDogs();
        assertTrue(mgmt.getDogs().isEmpty());
    }

    @Test
    @DisplayName("saveRecords/loadRecords — records survive save and reload")
    void testSaveAndLoadRecords() {
        izzy.addRecord(new TrainingRecord("Sit", "2026-03-15", Performance.EXCELLENT));
        izzy.addRecord(new TrainingRecord("Stay", "2026-03-20", Performance.GOOD));
        chase.addRecord(new TrainingRecord("Track", "2026-04-01", Performance.SATISFACTORY));

        mgmt.addDog(izzy);
        mgmt.addDog(chase);
        mgmt.saveDogs();
        mgmt.saveRecords();

        // Create a NEW DogManagement and reload everything
        DogManagement mgmt2 = new DogManagement();
        mgmt2.loadDogs();
        mgmt2.loadRecords();

        Dog loadedBella = mgmt2.searchByID(1);
        assertNotNull(loadedBella);
        assertEquals(2, loadedBella.getRecords().size());
        assertEquals("Sit", loadedBella.getRecords().get(0).getSkill());
        assertEquals(Performance.EXCELLENT, loadedBella.getRecords().get(0).getPerformance());
        assertEquals("Stay", loadedBella.getRecords().get(1).getSkill());
        assertEquals(Performance.GOOD, loadedBella.getRecords().get(1).getPerformance());

        Dog loadedRex = mgmt2.searchByID(2);
        assertNotNull(loadedRex);
        assertEquals(1, loadedRex.getRecords().size());
        assertEquals("Track", loadedRex.getRecords().get(0).getSkill());
        assertEquals(Performance.SATISFACTORY, loadedRex.getRecords().get(0).getPerformance());
    }

    @Test
    @DisplayName("loadRecords — no crash when file does not exist")
    void testLoadRecords_FileNotFound() {
        mgmt.addDog(izzy);
        mgmt.loadRecords();
        assertTrue(izzy.getRecords().isEmpty());
    }

    @Test
    @DisplayName("Full round-trip — all dogs and records persist correctly")
    void testFullRoundTrip() {
        // Arrange
        izzy.addRecord(new TrainingRecord("Comfort", "2026-01-10", Performance.EXCELLENT));
        chase.addRecord(new TrainingRecord("Sniff", "2026-02-15", Performance.GOOD));
        fin.addRecord(new TrainingRecord("Search", "2026-03-20", Performance.NEEDS_IMPROV));

        mgmt.addDog(izzy);
        mgmt.addDog(chase);
        mgmt.addDog(fin);

        // Act — Save
        mgmt.saveDogs();
        mgmt.saveRecords();

        // Act — Load into fresh manager
        DogManagement mgmt2 = new DogManagement();
        mgmt2.loadDogs();
        mgmt2.loadRecords();

        // Assert — Dogs
        assertEquals(3, mgmt2.getDogs().size());

        // Assert — Records attached to correct dogs
        assertEquals(1, mgmt2.searchByID(1).getRecords().size());
        assertEquals(1, mgmt2.searchByID(2).getRecords().size());
        assertEquals(1, mgmt2.searchByID(3).getRecords().size());

        // Assert — Record content
        assertEquals("Comfort", mgmt2.searchByID(1).getRecords().get(0).getSkill());
        assertEquals(Performance.EXCELLENT, mgmt2.searchByID(1).getRecords().get(0).getPerformance());
        assertEquals(Performance.NEEDS_IMPROV, mgmt2.searchByID(3).getRecords().get(0).getPerformance());
    }
}
