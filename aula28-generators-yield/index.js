function *lazy() {
    console.log("Producing A...")
    yield "A"
    console.log("Producing B...")
    yield "B"
    console.log("Producing C...")
    yield "C"
}

function eager() {
    return ["A", "B", "C"]
}

const seq = lazy()

// for (const item of seq) { console.log(item) }

console.log(seq.next().value)
console.log(seq.next().value)