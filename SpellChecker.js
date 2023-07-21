const dictionary = document.querySelector("#dictionary");
const userInput = document.querySelector(".in");
const btn = document.querySelector(".btn");
const res = document.querySelector(".res");
let set;

dictionary.addEventListener("change", function () {
	const file = new FileReader();
	file.readAsText(dictionary.files[0]);

	file.addEventListener("load", function () {
		const s = file.result;
		set = new Set(s.split("\n"));
	});

	btn.addEventListener("click", function (e){
		e.preventDefault();

		if (userInput.value) {
			let input = userInput.value.trim();
			let stringRes = "";
			if (!set.has(input)) {
				let res = corrections(input, set);
				for (const solution of res) {
					stringRes += `${solution} `;
				}
			}else {
				stringRes = `The word is correct`;
			}
			res.textContent = `${input} :- ${stringRes}`;
		}
	})
});

function corrections (badWord, dictionary) {
	let tree = new Set();
        // Delete any one of the letters from the misspelled word.
        for (let i = 0; i < badWord.length; i++) {
            let s = badWord.slice(0, i) + badWord.slice(i + 1);
            if (dictionary.has(s)) {
                tree.add(s);
            }
        }

	 // Change any letter in the misspelled word to any other letter.
        for (let i = 0; i < badWord.length; i++) {
            for (let ch = 'a'; ch <= 'z'; ch++) {
                let s = badWord.slice(0, i) + ch + badWord.slice(i + 1);
                if (dictionary.has(s)) {
                    tree.add(s);
                }
            }
        }

	 // Insert any letter at any point in the misspelled word.
        for (let i = 0; i < badWord.length; i++) {
            for (let ch = 'a'; ch <= 'z'; ch++) {
                let s = badWord.slice(0, i) + ch + badWord.slice(i);
                if (dictionary.has(s)) {
                    tree.add(s);
                }
            }
        }

        // Swap any two neighboring characters in the misspelled word.
        for (let i = 0; i < badWord.length - 1; i++) {
            for (let ch = 'a'; ch <= 'z'; ch++) {
                let s = badWord.slice(0, i) + badWord.slice(i + 1, i + 2) +
                        badWord.slice(i, i + 1) + badWord.slice(i + 2);
                if (dictionary.has(s)) {
                    tree.add(s);
                }
            }
        }

        // Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary)
        for (let i = 0; i < badWord.length; i++) {
                let s1 = badWord.slice(0, i); 
                let s2 = badWord.slice(i);
                if (dictionary.has(s1) && dictionary.has(s2)) {
                    tree.add(s1);
                    tree.add(s2);
                }
        }
        return tree;

}

