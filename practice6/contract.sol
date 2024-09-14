// SPDX-License-Identifier: MIT

pragma solidity ^0.8.21;

contract Exchange {
    string public name;
    string private doc;
    address private sender;

    event DocumentUpdated(string indexed newDoc);

    modifier onlySender() {
        require(msg.sender == sender, "Only the sender can update the document");
        _;
    }

    constructor(string memory document, string memory senderName) {
        name = senderName;
        sender = msg.sender;
        doc = document;
    }

    function setDoc(string memory newDoc) public onlySender {
        doc = newDoc;
        emit DocumentUpdated(newDoc);
    }

    function getSender() public view returns (address) {
        return sender;
    }

    function getDoc() public view returns (string memory) {
        return doc;
    }
}

