package services;

public class HashingServiceImpl implements HashingService {
    @Override
    public String hash(String value) {
        return "@" + value + "!";
    }
}
