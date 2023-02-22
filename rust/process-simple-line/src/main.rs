use std::io::BufRead;

use serde::{Deserialize, Serialize};

#[derive(Serialize, Deserialize, Debug)]
struct Input {
    pub id: String,
    pub a: u64,
    pub b: u64,
}

#[derive(Serialize, Deserialize, Debug)]
struct Output {
    pub id: String,
    pub result: u64,
}

fn process_line(line: String) -> Output {
    let input: Input = serde_json::from_str(&line).expect("could not parse json from input");

    let id = input.id;
    let result = input.a * 999 + input.b * 888;

    Output { id, result }
}

fn main() {
    for line in std::io::stdin().lock().lines() {
        if let Ok(line) = line {
            println!(
                "{}",
                serde_json::to_string(&process_line(line)).expect("could not convert to json")
            );
        }
    }
}
