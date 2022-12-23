package markerInterfaceTest;

// 호랑이를 육식동물로 묶어주기 위해서 CarnivoreMarker라는 마커 인터페이스를 지정받음
// Tiger의 타입은 3개 => Tiger, Animal, CarnivoreMarker
public class Tiger extends Animal implements CarnivoreMarker{;}
