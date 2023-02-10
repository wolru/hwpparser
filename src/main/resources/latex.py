import hml_equation_parser as hp
import sys

def main(script):
    print(hp.eq2latex(script))

if __name__ == "__main__":
    main(sys.argv[1])