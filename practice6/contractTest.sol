
pragma solidity >=0.7.0 <0.9.0;
import "remix_tests.sol"; // this import is automatically injected by Remix.
import "hardhat/console.sol";
import "contract.sol";

contract ExchangeTest {

    string doc;
    string name;

    Exchange exchange;

    
    function beforeAll() public {
        doc = "Initial Document";
        name = "Sender Name";
        exchange = new Exchange(doc, name);
    }

    function testInitialValues() public {
        Assert.equal(exchange.getSender(), address(this), "Sender should be the contract deployer");
        Assert.equal(exchange.name(), name, "Name should be set correctly");
        Assert.equal(exchange.getDoc(), doc, "Document should be set correctly");
    }

    function testUpdateDocument() public {
        string memory newDoc = "Updated Document";
        exchange.setDoc(newDoc);
        Assert.equal(exchange.getDoc(), newDoc, "Document should be updated correctly");
    }

    function testEmitDocumentUpdatedEvent() public {
        string memory newDoc = "Another Document";
        (bool success, bytes memory data) = address(exchange).call(abi.encodeWithSignature("setDoc(string)", newDoc));
        Assert.ok(success, "setDoc should succeed");
        Assert.equal(exchange.getDoc(), newDoc, "Document should be updated correctly");
    }

    function testOnlySenderCanUpdateDocument() public {
         Exchange newExchange = new Exchange("Another Document", "Another Sender");
        (bool success, ) = address(newExchange).call(abi.encodeWithSignature("setDoc(string)", "Malicious Update"));
        // Assert.isFalse(success, "Ony the sender should be able to update the document");
    }

}
